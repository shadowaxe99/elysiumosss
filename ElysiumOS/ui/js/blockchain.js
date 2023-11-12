// blockchain.js - Handles blockchain interactions for Elysium OS

const Web3 = require('web3');
const web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'));

// Shared variables
let smartContractAddress;
let inGameCurrencyBalance;
let blockchainTransactionData;

// Initialize smart contract
const contractABI = require('./contractABI.json');
const contract = new web3.eth.Contract(contractABI, smartContractAddress);

// Function to check user's in-game currency balance
function checkInGameCurrency(userId) {
  contract.methods.balanceOf(userId).call()
    .then(balance => {
      inGameCurrencyBalance = balance;
      console.log(`User's in-game currency balance is: ${balance}`);
    })
    .catch(error => console.error(error));
}

// Function to process in-game transactions
function processTransaction(fromId, toId, amount, transactionData) {
  contract.methods.transfer(fromId, toId, amount).send({ from: fromId })
    .on('transactionHash', hash => {
      console.log(`Transaction hash: ${hash}`);
      blockchainTransactionData = hash;
    })
    .on('receipt', receipt => {
      console.log(`Transaction receipt: ${receipt}`);
    })
    .on('error', error => {
      console.error(`Transaction failed: ${error.message}`);
    });
}

// Function to handle NFT ownership transfer
function transferNFT(fromId, toId, tokenId) {
  contract.methods.transferFrom(fromId, toId, tokenId).send({ from: fromId })
    .on('transactionHash', hash => {
      console.log(`NFT transfer transaction hash: ${hash}`);
    })
    .on('receipt', receipt => {
      console.log(`NFT transfer receipt: ${receipt}`);
      // Emit event for NFT ownership change
      document.dispatchEvent(new CustomEvent('NFTOwnershipChanged', { detail: { tokenId, fromId, toId } }));
    })
    .on('error', error => {
      console.error(`NFT transfer failed: ${error.message}`);
    });
}

// Function to connect to the blockchain network
function connectToBlockchain() {
  web3.eth.net.isListening()
    .then(() => console.log('Connected to the blockchain network'))
    .catch(e => console.error('Blockchain network connection failed', e));
}

// Initialize blockchain connection on load
connectToBlockchain();

// Export functions for use in other modules
module.exports = {
  checkInGameCurrency,
  processTransaction,
  transferNFT
};