package blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ArbitrumIntegration {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final String networkUrl;

    public ArbitrumIntegration(String privateKey, String networkUrl) {
        this.web3j = Web3j.build(new HttpService(networkUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
        this.networkUrl = networkUrl;
    }

    public String deployContract(String contractBinary) throws Exception {
        Contract contract = Contract.deploy(
                web3j, credentials, gasProvider, contractBinary, BigInteger.ZERO, Contract.GAS_LIMIT
        ).send();
        return contract.getContractAddress();
    }

    public TransactionReceipt sendTransaction(String contractAddress, Function function) throws Exception {
        TransactionManager transactionManager = new RawTransactionManager(
                web3j, credentials
        );
        String encodedFunction = FunctionEncoder.encode(function);

        return transactionManager.sendTransaction(
                gasProvider.getGasPrice(),
                gasProvider.getGasLimit(),
                contractAddress,
                encodedFunction,
                BigInteger.ZERO
        ).send();
    }

    public List<Type> callSmartContractFunction(Function function, String contractAddress) throws ExecutionException, InterruptedException {
        String encodedFunction = FunctionEncoder.encode(function);

        org.web3j.protocol.core.methods.response.EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(
                        credentials.getAddress(), contractAddress, encodedFunction
                ),
                DefaultBlockParameterName.LATEST
        ).sendAsync().get();

        return FunctionReturnDecoder.decode(
                response.getValue(), function.getOutputParameters()
        );
    }

    public String getSmartContractEventValue(String contractAddress, String eventName) throws Exception {
        List<Type> indexedValues = Collections.emptyList();
        List<TypeReference<?>> nonIndexedValues = Arrays.asList(new TypeReference<Uint256>() {});

        Event event = new Event(eventName, indexedValues, nonIndexedValues);

        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        );

        filter.addSingleTopic(EventEncoder.encode(event));

        EthLog ethLog = web3j.ethGetLogs(filter).send();

        for (EthLog.LogResult<?> logResult : ethLog.getLogs()) {
            Log log = (Log) logResult.get();
            List<String> topics = log.getTopics();
            String data = log.getData();
            // Decode the data and topics to get the event values
        }

        // Return the value from the event
        return "";
    }
}