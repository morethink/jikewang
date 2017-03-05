package studio.geek.dao;

import org.springframework.stereotype.Repository;
import studio.geek.entity.Production;
import studio.geek.util.Page;

import java.util.List;


@Repository
public interface IProductionDao {

    Production getProductionByName(String name);

    int saveProduction(Production production);

    int deleteProduction(String name);

    int deleteProductions(List<String> names);



    List<Production> listProductionsByName(Page page);

    List<Production> listProductions(Page page);

    int updateProduction(Production production);
}
