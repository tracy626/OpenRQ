package net.fec.openrq.parameters;

/**
 * Deriver class for data parameters. This class follows the "Builder" design pattern, where multiple properties may be
 * configured and a final result {@code DataParameters} instance is returned upon calling the method {@link #derive()}.
 * <p>
 * Data parameters can be built from the following assignable properties:
 * <ul>
 * <li>maximum payload length</li>
 * <li>maximum block size in working memory</li>
 * <li>minimum sub-symbol size</li>
 * </ul>
 * <p>
 * If some property is not assigned, a default value is automatically assigned to it. Default values for each property
 * are defined as static fields.
 * <p>
 * All property assigning methods return the {@code this} instance in order to allow chained invocation:
 * 
 * <pre>
 * DataParameters params = new ParametersDeriver(dataLen)
 *                             .maxPayload(maxPay)
 *                             .maxBlockInMemory(maxBlock)
 *                             .derive();
 * </pre>
 * 
 * @author Jos&#233; Lopes &lt;jlopes&#064;lasige.di.fc.ul.pt&gt;
 * @author Ricardo Fonseca &lt;ricardof&#064;lasige.di.fc.ul.pt&gt;
 */
public final class DataParametersDeriver {

    /**
     * Default value of 4 for the symbol alignment.
     */
    public static final int DEF_SYMBOL_ALIGNMENT = 4;      // Al

    /**
     * Default value of 1392 for the maximum payload length.
     */
    public static final int DEF_MAX_PAYLOAD_LENGTH = 1392; // P'

    /**
     * Default value of 76800 for the maximum block size.
     */
    public static final int DEF_MAX_BLOCK_SIZE = 76800;    // WS // B

    /**
     * Default value of 8 for the minimum sub-symbol size.
     */
    public static final int DEF_MIN_SUB_SYMBOL = 8;        // SS

    private final long dataLen;

    private int maxPayloadLen; // P'
    private int maxBlock;      // WS
    private int minSubSymbol;  // SS


    // TODO add alignment parameter

    /**
     * Constructs a new {@code DataParametersDeriver} instance with the provided data length.
     * 
     * @param dataLen
     *            The length of the encodable data in number of bytes
     * @exception IllegalArgumentException
     *                If {@code ParameterChecker.isValidDataLength(dataLen) == false}
     */
    public DataParametersDeriver(long dataLen) {

        if (!ParameterChecker.isValidDataLength(dataLen)) {
            throw new IllegalArgumentException("invalid data length");
        }

        this.dataLen = dataLen;

        this.maxPayloadLen = DEF_MAX_PAYLOAD_LENGTH;
        this.maxBlock = DEF_MAX_BLOCK_SIZE;
        this.minSubSymbol = DEF_MIN_SUB_SYMBOL;
    }

    /**
     * Assigns the provided value to the property of <i>maximum payload length in number of bytes</i>.
     * <p>
     * This property affects the maximum size of an encoding symbol, which will be equal to the provided payload length
     * rounded down to the closest multiple of {@code Al}, where {@code Al} is the symbol alignment parameter.
     * 
     * @param maxPayloadLen
     *            The maximum payload length in number of bytes
     * @return this deriver
     * @exception IllegalArgumentException
     *                If {@code maxPayloadLen} is non-positive
     */
    public DataParametersDeriver maxPayload(int maxPayloadLen) {

        if (maxPayloadLen <= 0) throw new IllegalArgumentException("non-positive value");
        // TODO replace default symbol alignment value with field value if we'll have one sometime
        this.maxPayloadLen = (maxPayloadLen / DEF_SYMBOL_ALIGNMENT) * DEF_SYMBOL_ALIGNMENT;
        return this;
    }

    /**
     * Assigns the default value to the property of <i>maximum payload length in number of bytes</i>.
     * 
     * @return this deriver
     * @see #maxPayload(int)
     */
    public DataParametersDeriver defaultMaxPayload() {

        this.maxPayloadLen = DEF_MAX_PAYLOAD_LENGTH;
        return this;
    }

    /**
     * Assigns the provided value to the property of <i>maximum block size in number of bytes that is decodable in
     * working memory</i>.
     * <p>
     * This property allows the decoder to work with a limited amount of memory in an efficient way.
     * 
     * @param maxBlock
     *            A number of bytes indicating the maximum block size that is decodable in working memory
     * @return this deriver
     * @exception IllegalArgumentException
     *                If {@code maxBlock} is non-positive
     */
    public DataParametersDeriver maxBlockInMemory(int maxBlock) {

        if (maxBlock <= 0) throw new IllegalArgumentException("non-positive value");
        this.maxBlock = maxBlock;
        return this;
    }

    /**
     * Assigns the default value to the property of <i>maximum block size in number of bytes that is decodable in
     * working memory</i>.
     * 
     * @return this deriver
     * @see #maxBlockInMemory(int)
     */
    public DataParametersDeriver defaultMaxBlockInMemory() {

        this.maxPayloadLen = DEF_MAX_PAYLOAD_LENGTH;
        return this;
    }

    /**
     * Assigns the provided value to the property of <i>the lower bound on the sub-symbol size in units of {@code Al},
     * where {@code Al} is the symbol alignment parameter</i>.
     * <p>
     * This property affects the amount of interleaving used by the partitioning of an object into source blocks and
     * sub-blocks.
     * 
     * @param minSubSymbol
     *            The lower bound on the sub-symbol size in units of {@code Al}
     * @return this deriver
     * @exception IllegalArgumentException
     *                If {@code minSubSymbol} is non-positive
     */
    public DataParametersDeriver minSubSymbol(int minSubSymbol) {

        if (minSubSymbol <= 0) throw new IllegalArgumentException("non-positive value");
        this.minSubSymbol = minSubSymbol;
        return this;
    }

    /**
     * Assigns the default value to the property of <i>the lower bound on the sub-symbol size in units of {@code Al},
     * where {@code Al} is the symbol alignment parameter</i>.
     * 
     * @return this deriver
     * @see #minSubSymbol(int)
     */
    public DataParametersDeriver defaultMinSubSymbol() {

        this.maxPayloadLen = DEF_MAX_PAYLOAD_LENGTH;
        return this;
    }

    /**
     * Returns data parameters derived from the currently assigned properties.
     * 
     * @return data parameters derived from the currently assigned properties
     */
    public DataParameters derive() {

        // TODO derive F, T, Z, N and return the parameters
        return null;
    }
}