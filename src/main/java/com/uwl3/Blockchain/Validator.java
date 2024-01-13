package com.uwl3.Blockchain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@EnableScheduling
@Configuration
@Service
public class Validator {

    @Autowired
    private BlockchainLedger blockchainLedger;

    @Scheduled(cron = "0 */2 * * * *")
    public void runChainValidator(){
        List<Block> blockList = blockchainLedger.getBlockchain();
        if(blockList.size()>=2){
            log.info("Starting node analysis to validate blockchain ledger");
            if (isChainValid()){
                log.info("Blockchain is valid");
            }
        }
    }

    public Boolean isChainValid()
    {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1;
             i < blockchainLedger.getBlockchain().size();
             i++) {

            currentBlock = blockchainLedger.getBlockchain().get(i);
            previousBlock = blockchainLedger.getBlockchain().get(i - 1);

            if (!currentBlock.hash
                    .equals(
                            currentBlock
                                    .calculateHash())) {
               log.info(
                        "Hashes are not equal");
                return false;
            }

            if (!previousBlock
                    .hash
                    .equals(
                            currentBlock
                                    .previousHash)) {
               log.info(
                        "Previous Hashes are not equal");
                return false;
            }
        }

        return true;
    }

}
