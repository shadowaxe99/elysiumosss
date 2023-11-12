package blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.protocol.core.DefaultBlockParameterName;
import contracts.TokenContract;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class BlockchainManager {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final String smartContractAddress;
    private TokenContract tokenContract;

    public BlockchainManager(String ethereumNodeUrl, String privateKey, String smartContractAddress) {
        this.web3j = Web3j.build(new HttpService(ethereumNodeUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
        this.smartContractAddress = smartContractAddress;
        this.tokenContract = loadContract();
    }

    private TokenContract loadContract() {
        return TokenContract.load(smartContractAddress, web3j, credentials, gasProvider);
    }

    public CompletableFuture<TransactionReceipt> transferTokens(String toAddress, BigInteger amount) {
        return tokenContract.transfer(toAddress, amount).sendAsync();
    }

    public BigInteger getBalance(String address) {
        try {
            return tokenContract.balanceOf(address).send();
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    public CompletableFuture<TransactionReceipt> mintTokens(BigInteger amount) {
        return tokenContract.mint(credentials.getAddress(), amount).sendAsync();
    }

    public String deployNewContract() {
        try {
            TokenContract newContract = TokenContract.deploy(web3j, credentials, gasProvider).send();
            return newContract.getContractAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BigInteger getTokenTotalSupply() {
        try {
            return tokenContract.totalSupply().send();
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    public CompletableFuture<TransactionReceipt> burnTokens(BigInteger amount) {
        return tokenContract.burn(amount).sendAsync();
    }

    public String getContractOwner() {
        try {
            return tokenContract.owner().send();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isTransactionSuccessful(TransactionReceipt transactionReceipt) {
        return !transactionReceipt.isStatusOK();
    }

    public void shutdown() {
        web3j.shutdown();
    }
}