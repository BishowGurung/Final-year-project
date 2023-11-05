package com.uwl3.domain.cache;

import com.uwl3.domain.dao.HealthNews;
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
public class NewsCache {
    List<HealthNews> healthNewsList = new ArrayList<>();
}
