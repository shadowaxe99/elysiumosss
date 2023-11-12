package blockchain;

import java.math.BigInteger;
import java.util.HashMap;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import contracts.TokenERC20;

public class TokenManager {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final String smartContractAddress;
    private final TokenERC20 tokenContract;

    public TokenManager(String ethereumNodeUrl, String privateKey, String smartContractAddress) {
        this.web3j = Web3j.build(new HttpService(ethereumNodeUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
        this.smartContractAddress = smartContractAddress;
        this.tokenContract = TokenERC20.load(smartContractAddress, web3j, credentials, gasProvider);
    }

    public TransactionReceipt transferTokens(String toAddress, BigInteger amount) throws Exception {
        return tokenContract.transfer(toAddress, amount).send();
    }

    public BigInteger getBalance(String address) throws Exception {
        return tokenContract.balanceOf(address).send();
    }

    public TransactionReceipt mintTokens(String toAddress, BigInteger amount) throws Exception {
        return tokenContract.mint(toAddress, amount).send();
    }

    public TransactionReceipt burnTokens(String fromAddress, BigInteger amount) throws Exception {
        return tokenContract.burn(fromAddress, amount).send();
    }

    public String deployNewTokenContract(String tokenName, String tokenSymbol, BigInteger initialSupply) throws Exception {
        TokenERC20 newTokenContract = TokenERC20.deploy(
                web3j, credentials, gasProvider,
                tokenName, tokenSymbol, initialSupply).send();
        return newTokenContract.getContractAddress();
    }

    public static void main(String[] args) {
        // Example usage
        String ethereumNodeUrl = "https://mainnet.infura.io/v3/YOUR_INFURA_KEY";
        String privateKey = "YOUR_PRIVATE_KEY";
        String contractAddress = "YOUR_CONTRACT_ADDRESS";

        TokenManager manager = new TokenManager(ethereumNodeUrl, privateKey, contractAddress);

        try {
            BigInteger amount = Convert.toWei("1", Convert.Unit.ETHER).toBigInteger();
            String toAddress = "0xRecipientAddressHere";
            TransactionReceipt transactionReceipt = manager.transferTokens(toAddress, amount);
            System.out.println("Transfer successful: " + transactionReceipt.getTransactionHash());
        } catch (Exception e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }
    }
}