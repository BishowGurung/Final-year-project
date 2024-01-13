package com.uwl3.Blockchain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Service
public class BlockchainLedger {
    public List<Block> blockchain
            = new ArrayList<Block>();
}
