package com.tourism.safety.tourist_safety_system.Service;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tourism.safety.tourist_safety_system.Util.HashUtil;
import com.tourism.safety.tourist_safety_system.Model.BlockchainTourist;
import com.tourism.safety.tourist_safety_system.Blockchain.FabricClient;

@Service
public class BlockchainService {

    // ✅ Store Tourist Data on Blockchain
    public void storeTouristOnChain(String touristId, String passportNumber, String aadhaarNumber) {
        try {
            // Convert sensitive info into hash
            String passportHash = HashUtil.generateHash(passportNumber);
            String aadhaarHash = HashUtil.generateHash(aadhaarNumber);

            // Prepare blockchain transaction
            String[] args = { touristId, passportHash, aadhaarHash };

            // Call chaincode (smart contract)
            FabricClient.invokeChaincode("touristContract", "registerTourist", args);

        } catch (Exception e) {
            throw new RuntimeException("Failed to store tourist on blockchain", e);
        }
    }

    // ✅ Verify Tourist Existence on Blockchain
    public boolean verifyTouristOnChain(String touristId) {
        try {
            String result = FabricClient.queryChaincode("touristContract", "getTourist", new String[]{ touristId });
            return (result != null && !result.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException("Blockchain verification failed", e);
        }
    }

    // ✅ Get Tourist Data from Blockchain
    public BlockchainTourist getTouristFromChain(String touristId) {
        try {
            String result = FabricClient.queryChaincode("touristContract", "getTourist", new String[]{ touristId });
            if (result == null || result.isEmpty()) {
                return null;
            }

            // Convert JSON from blockchain into POJO
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, BlockchainTourist.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch tourist from blockchain", e);
        }
    }
}
