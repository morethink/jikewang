package studio.geek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studio.geek.dao.IMemberDao;
import studio.geek.entity.Member;
import studio.geek.exception.ErrorException;
import studio.geek.util.FileUtil;
import studio.geek.util.Page;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Service
public class MemberService {

    @Autowired
    private FileUtil fileUtil;

    @Resource
    private IMemberDao iMemberDao;

    Member getMemberByMemberId(String memberId) {
        return iMemberDao.getMemberByMemberId(memberId);
    }


    public boolean saveMember(MultipartFile file, Member member) {

        if (getMemberByMemberId(member.getMemberId()) != null) {
            throw new ErrorException("学号已存在");
        } else {
            if (member.getMemberId() == null) {
                throw new ErrorException("学号不为空");
            }
            if (member.getName() == null) {
                throw new ErrorException("名字不为空");
            }

            Pattern pattern = Pattern.compile("^\\d{10}$");

            if (pattern.matcher(member.getMemberId()).matches() == false) {
                throw new ErrorException("学号难道不是10位,你可能是记错了哟");
            }

            if (member.getSex() != null &&
                    !member.getSex().equals("女") &&
                    !member.getSex().equals("男")) {
                throw new ErrorException("你的性别可能是有点奇怪啊");
            }

            String photoAddress;
            if (file != null && !file.isEmpty()) {
                photoAddress = fileUtil.upload(file);
                member.setPhoto(photoAddress);
            }
            if (iMemberDao.saveMember(member) == 1) {

                return true;
            }
        }
        return false;
    }

    /**
     * 删除成员
     *
     * @param memberId
     * @return
     */
    public boolean deleteMember(String memberId) {
        Member member = getMemberByMemberId(memberId);

        if (member == null) {
            throw new ErrorException("学号不存在");
        } else {
            if (iMemberDao.deleteMember(memberId) == 1) {
                String photo = member.getPhoto();
                if (photo != null) {
                    fileUtil.delete(photo);
                }
                return true;
            }
            return false;

        }
    }

    /**
     * 批量删除成员
     *
     * @param memberIds
     * @return
     */

    public boolean deleteMembers(List<String> memberIds) {

        List<Member> members = new ArrayList<Member>();

        for (String memberId : memberIds) {
            Member member = getMemberByMemberId(memberId);
            if (member == null) {
                throw new ErrorException(memberId + "学号不存在");
            }
            members.add(member);
        }
        if (iMemberDao.deleteMembers(memberIds) >= 1) {
            for (Member member : members) {
                String photo = member.getPhoto();
                if (photo != null) {
                    fileUtil.delete(photo);
                }
            }
            return true;
        }
        return false;

    }


    /**
     * 通过学号查询成员
     *
     * @param page
     * @return
     */
    public List<Member> listMembersById(Page page) {

        return iMemberDao.listMembersById(page);
    }

    /**
     * 通过名字查询成员
     *
     * @param page
     * @return
     */

    public List<Member> listMembersByName(Page page) {


        return iMemberDao.listMembersByName(page);
    }

    /**
     * list现在的所有成员
     *
     * @param page
     * @return
     */
    public List<Member> listCurrentMembers(Page page) {

        return iMemberDao.listCurrentMembers(page);
    }

    /**
     * list已经毕业的成员
     *
     * @param page
     * @return
     */
    public List<Member> listOldMembers(Page page) {

        return iMemberDao.listOldMembers(page);
    }

    public boolean updateMember(MultipartFile file, Member member, String originalMemberId) {
        if (member.getName() == null) {
            throw new ErrorException("名字不为空");
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if (pattern.matcher(member.getMemberId()).matches() == false) {
            throw new ErrorException("学号难道不是10位,你可能是记错了哟");
        }

        //判断是否修改学号

        Member originalMember = getMemberByMemberId(originalMemberId);
        if (originalMember == null) {
            throw new ErrorException("你的学号怕是填错了哦");
        }
        if (!originalMemberId.equals(member.getMemberId())) {
            Member currentMember = getMemberByMemberId(member.getMemberId());
            if (currentMember != null) {
                throw new ErrorException("你所要修改的学号已经存在");
            }
        }
//        System.out.println(originalMember.getId());
        int id = originalMember.getId();
        if (id != 0)
            member.setId(id);
        System.out.println(member.getId());
        //保存文件
        String photoAddress;
        boolean isDeletePhoto = false;
        if (file != null && !file.isEmpty()) {
            photoAddress = fileUtil.upload(file);
            member.setPhoto(photoAddress);
            isDeletePhoto = true;
        }

        if (iMemberDao.updateMember(member) == 1) {
            if (isDeletePhoto) {
                fileUtil.delete(originalMember.getPhoto());
            }
            return true;
        }
        return false;
    }

}
