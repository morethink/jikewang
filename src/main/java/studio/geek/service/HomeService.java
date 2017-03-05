package studio.geek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.geek.dao.IHomeDao;
import studio.geek.entity.Candidate;
import studio.geek.entity.Member;
import studio.geek.entity.Production;
import studio.geek.exception.ErrorException;
import studio.geek.util.CurrentMember;
import studio.geek.util.JsonUtil;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 李文浩
 * @version 2017/2/21.
 */

@Service
public class HomeService {

    @Autowired
    private IHomeDao iHomeDao;

    public List<Member> getAllOldMembers() {

        return iHomeDao.getAllOldMembers();
    }

    public List<CurrentMember> getCurrentMembersByGrade(String grade) {

        return iHomeDao.getCurrentMembersByGrade(grade);
    }

    public List<Production> getAllProductions() {

        return iHomeDao.getAllProductions();
    }


    public boolean saveCandidate(Candidate candidate) {
        JsonUtil.prettyPrint(candidate);
        if (candidate.getCandidateId() == null || candidate.getCandidateId().equals("")) {
            throw new ErrorException("学号不为空");
        }
        if (candidate.getName() == null || candidate.getName().equals("")) {
            throw new ErrorException("名字不为空");
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");

        if (pattern.matcher(candidate.getCandidateId()).matches() == false) {
            throw new ErrorException("学号难道不是10位,你可能是记错了哟");
        }

        if (getCandidateById(candidate.getCandidateId()) != null) {
            throw new ErrorException("候选人已经存在");
        }

        if (iHomeDao.saveCandidate(candidate) == 1) {
            return true;
        }
        return false;
    }

    public Candidate getCandidateById(String candidateId) {

        return iHomeDao.getCandidateById(candidateId);
    }
}
