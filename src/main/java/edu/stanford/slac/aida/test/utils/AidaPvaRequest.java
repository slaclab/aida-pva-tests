/**
 * @file
 * @brief Class to create and execute AIDA-PVA requests.
 * Use static members of this class to create and execute AIDA-PVA requests.  It
 * works using the builder pattern.
 */
package edu.stanford.slac.aida.test.utils;

import org.epics.pvaccess.client.rpc.RPCClientImpl;
import org.epics.pvaccess.server.rpc.RPCRequestException;
import org.epics.pvdata.factory.FieldFactory;
import org.epics.pvdata.factory.PVDataFactory;
import org.epics.pvdata.pv.*;

import static edu.stanford.slac.aida.test.utils.AidaType.*;

/**
 * This is a general purpose AIDA PVA Request Executor
 * It follows the builder pattern for maximum configurability
 * You can use this general code to get any AIDA-PVA request
 */
public class AidaPvaRequest {
    /**
     * To be able to create fields in PV Access you need a field creator factory
     */
    private final static FieldCreate fieldCreate = FieldFactory.getFieldCreate();

    /**
     * The argument Builder which is used to add any desired arguments
     */
    private final ArgumentBuilder argumentBuilder = new ArgumentBuilder();

    private final String channelName;
    private final String message;
    private AidaType queryType = null;
    private Boolean isForChar = false;
    private Boolean isForCharArray = false;
    private Boolean expectToFail = false;

    /**
     * Constructor
     *
     * @param channelName the request you want to get your request against
     */
    AidaPvaRequest(String channelName) {
        this.channelName = channelName;
        this.message = "";
    }

    /**
     * Constructor
     *
     * @param channelName the request you want to get your request against
     */
    AidaPvaRequest(String channelName, String message) {
        this.channelName = channelName;
        this.message = message;
    }

    /**
     * To add an argument to the request
     *
     * @param name  name of argument
     * @param value to set for argument
     * @return AidaPvaRequestExecutor
     */
    public AidaPvaRequest with(String name, Object value) {
        argumentBuilder.addArgument(name, value);
        return this;
    }

    /**
     * To set returning argument of the request
     *
     * @param type to set
     * @return AidaPvaRequestExecutor
     */
    public AidaPvaRequest returning(AidaType type) {
        queryType = type;
        if (type.equals(AIDA_CHAR)) {
            isForChar = true;
            type = AIDA_BYTE;
        } else if (type.equals(AIDA_CHAR_ARRAY)) {
            isForCharArray = true;
            type = AIDA_BYTE_ARRAY;
        }
        argumentBuilder.addArgument("TYPE", type.string());
        return this;
    }

    /**
     * To set VALUE argument of the request and execute the request
     *
     * @param value to set
     */
    public void set(Object value) {
        argumentBuilder.addArgument("VALUE", value);
        AidaPvaTestUtils.testCaseHeader(channelName, argumentBuilder.toString(), (queryType != null ? queryType.string() : null), true);
        AidaPvaTestUtils.displayResult(() -> setter(null), message, false, expectToFail);
    }

    /**
     * To set VALUE argument of the request and execute the request but expect it to fail
     *
     * @param value to set
     */
    public void setAndExpectFailure(Object value) {
        expectToFail = true;
        argumentBuilder.addArgument("VALUE", value);
        AidaPvaTestUtils.testCaseHeader(channelName, argumentBuilder.toString(), (queryType != null ? queryType.string() : null), true);
        AidaPvaTestUtils.displayResult(() -> setter(null), message, false, expectToFail);
    }

    /**
     * To get the query.  This will add all the arguments you've specified
     * and then get the request
     */
    public <T extends PVField> void get() {
        AidaPvaTestUtils.testCaseHeader(channelName, argumentBuilder.toString(), (queryType != null ? queryType.string() : null), true);
        AidaPvaTestUtils.displayResult(this::getter, message, isForChar || isForCharArray, expectToFail);
    }

    /**
     * To get the query but expect to fail.  This will add all the arguments you've specified
     * and then get the request
     */
    public <T extends PVField> void getAndExpectFailure() {
        expectToFail = true;
        get();
    }

    /**
     * To get the query.  This will add all the arguments you've specified
     * and then get the request
     *
     * @return the result of the request
     */
    PVStructure getter() throws RPCRequestException {
        return execute();
    }

    /**
     * To set VALUE argument of the request and execute the request
     *
     * @param value to set
     * @return AidaPvaRequestExecutor
     */
    PVStructure setter(Object value) throws RPCRequestException {
        if (value != null) {
            argumentBuilder.addArgument("VALUE", value);
        }
        return execute();
    }

    /**
     * Execute a request and return the PVStructure result.  Exceptions are thrown to caller
     *
     * @return the PVStructure result
     * @throws RPCRequestException if there is an error calling the request
     */
    private PVStructure execute() throws RPCRequestException {
        // Build the arguments structure
        var arguments = argumentBuilder.build();

        // Build the uri structure
        var uriStructure =
                fieldCreate.createStructure(AidaType.NTURI_ID,
                        new String[]{"path", "scheme", "query"},
                        new Field[]{fieldCreate.createScalar(ScalarType.pvString), fieldCreate.createScalar(ScalarType.pvString), arguments}
                );

        // Make the query (contains the uri and arguments
        var request = PVDataFactory.getPVDataCreate().createPVStructure(uriStructure);
        request.getStringField("scheme").put("pva");

        // Set the request path
        request.getStringField("path").put(channelName);
        // Set the request query values
        var query = request.getStructureField("query");
        argumentBuilder.initializeQuery(query);

        // Execute the query
        var client = new RPCClientImpl(channelName);
        var result = client.request(request, 3.0);
        return result;
    }
}
