package studio.geek.dao;


import org.springframework.stereotype.Repository;
import studio.geek.entity.Candidate;
import studio.geek.util.Page;

import java.util.List;

@Repository
public interface ICandidateDao {

    List<Candidate> listCandidates(Page page);


}
