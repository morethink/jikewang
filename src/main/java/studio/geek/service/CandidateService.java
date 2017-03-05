package studio.geek.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.geek.dao.ICandidateDao;
import studio.geek.entity.Candidate;
import studio.geek.util.Page;

import java.util.List;

/**
 * Created by think on 2017/1/16.
 */

@Service("candidateService")
public class CandidateService {

    @Autowired
    private ICandidateDao iCandidateDao;

    public List<Candidate> listCandidates(Page page) {
        return iCandidateDao.listCandidates(page);
    }
}
