package blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class SmartContractHandler {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final TransactionManager transactionManager;

    public SmartContractHandler(String ethereumNodeUrl, String privateKey) {
        this.web3j = Web3j.build(new HttpService(ethereumNodeUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
        this.transactionManager = new RawTransactionManager(web3j, credentials);
    }

    public <T extends Contract> T loadContract(String contractAddress, Class<T> contractClass) {
        return T.load(contractAddress, web3j, credentials, gasProvider);
    }

    public CompletableFuture<TransactionReceipt> executeContractTransaction(Contract contract, String functionName, BigInteger value, Object... args) {
        try {
            return (CompletableFuture<TransactionReceipt>) contract.getClass()
                    .getMethod(functionName, BigInteger.class, Object[].class)
                    .invoke(contract, value, args);
        } catch (Exception e) {
            throw new RuntimeException("Error executing contract transaction", e);
        }
    }

    public String deployContract(Class<? extends Contract> contractClass, Object... constructorArgs) {
        try {
            Contract contract = contractClass.getConstructor(Web3j.class, Credentials.class, ContractGasProvider.class)
                    .newInstance(web3j, credentials, gasProvider);
            return contract.deploy(web3j, credentials, gasProvider, constructorArgs).send().getContractAddress();
        } catch (Exception e) {
            throw new RuntimeException("Error deploying smart contract", e);
        }
    }

    public BigInteger queryContract(Contract contract, String functionName, Object... args) {
        try {
            return (BigInteger) contract.getClass()
                    .getMethod(functionName, Object[].class)
                    .invoke(contract, new Object[]{args});
        } catch (Exception e) {
            throw new RuntimeException("Error querying smart contract", e);
        }
    }

    public void shutdown() {
        web3j.shutdown();
    }
}