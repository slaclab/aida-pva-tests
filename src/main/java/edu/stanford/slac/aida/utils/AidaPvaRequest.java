package edu.stanford.slac.aida.utils;

import org.epics.pvaccess.client.rpc.RPCClientImpl;
import org.epics.pvaccess.server.rpc.RPCRequestException;
import org.epics.pvdata.factory.FieldFactory;
import org.epics.pvdata.factory.PVDataFactory;
import org.epics.pvdata.pv.*;

import java.util.List;
import java.util.stream.Collectors;

import static edu.stanford.slac.aida.utils.AidaType.*;

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

    /**
     * Constructor
     *
     * @param channelName the channel you want to get your request against
     */
    public AidaPvaRequest(String channelName) {
        this.channelName = channelName;
        this.message = "";
    }

    /**
     * Constructor
     *
     * @param channelName the channel you want to get your request against
     */
    public AidaPvaRequest(String channelName, String message) {
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
        if (type.equals(CHAR)) {
            isForChar = true;
            type = BYTE;
        } else if (type.equals(CHAR_ARRAY)) {
            isForCharArray = true;
            type = BYTE_ARRAY;
        }
        argumentBuilder.addArgument("TYPE", type.toString());
        return this;
    }

    /**
     * To set VALUE argument of the request and execute the request
     *
     * @param value to set
     */
    public void set(Object value) {
        argumentBuilder.addArgument("VALUE", value.toString());
        AidaPvaTestUtils.testCaseHeader(channelName, argumentBuilder.toString(), (queryType != null ? queryType.toString() : null), true);
        AidaPvaTestUtils.displayResult(() -> setter(null), message, false);
    }

    /**
     * To set VALUE argument of the request and execute the request
     *
     * @param value to set
     * @return AidaPvaRequestExecutor
     */
    public PVStructure setter(Object value) throws RPCRequestException {
        if (value != null) {
            argumentBuilder.addArgument("VALUE", value.toString());
        }
        return execute();
    }

    /**
     * To get the query.  This will add all the arguments you've specified
     * and then get the request
     */
    public <T extends PVField> void get() {
        AidaPvaTestUtils.testCaseHeader(channelName, argumentBuilder.toString(), (queryType != null ? queryType.toString() : null), true);
        AidaPvaTestUtils.displayResult(this::getter, message, isForChar || isForCharArray);
    }

    /**
     * To get the query.  This will add all the arguments you've specified
     * and then get the request
     *
     * @return the result of the request
     */
    public PVStructure getter() throws RPCRequestException {
        return execute();
    }

    /**
     * Execute a request and return the PVStructure result.  Exceptions are thrown to caller
     *
     * @return the PVStructure result
     * @throws RPCRequestException if there is an error calling the channel
     */
    private PVStructure execute() throws RPCRequestException {
        // Build the arguments structure
        Structure arguments = argumentBuilder.build();

        // Build the uri structure
        Structure uriStructure =
                fieldCreate.createStructure(NTURI_ID,
                        new String[]{"path", "scheme", "query"},
                        new Field[]{fieldCreate.createScalar(ScalarType.pvString), fieldCreate.createScalar(ScalarType.pvString), arguments}
                );

        // Make the query (contains the uri and arguments
        PVStructure request = PVDataFactory.getPVDataCreate().createPVStructure(uriStructure);
        request.getStringField("scheme").put("pva");

        // Set the request path
        request.getStringField("path").put(channelName);
        // Set the request query values
        PVStructure query = request.getStructureField("query");
        argumentBuilder.initializeQuery(query);

        // Execute the query
        RPCClientImpl client = new RPCClientImpl(channelName);
        PVStructure result = client.request(request, 3.0);
        client.destroy();
        return result;
    }
}
