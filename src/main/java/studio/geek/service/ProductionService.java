package studio.geek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studio.geek.dao.IProductionDao;
import studio.geek.entity.Production;
import studio.geek.exception.ErrorException;
import studio.geek.util.FileUtil;
import studio.geek.util.Page;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2017/2/1.
 */

@Service
public class ProductionService {


    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private IProductionDao iProductionDao;

    /**
     * 根据名字查询项目
     *
     * @param name
     * @return
     */
    Production getProductionByName(String name) {

        return iProductionDao.getProductionByName(name);
    }

    /**
     * 添加项目
     *
     * @param file
     * @param production
     * @return
     */

    public boolean saveProduction(MultipartFile file, Production production) {

        if (getProductionByName(production.getName()) != null) {
            throw new ErrorException("项目名字已存在");
        } else {

            if (production.getName() == null) {
                throw new ErrorException("名字不为空");
            }

            if (!file.isEmpty()) {
                String effectPicture = fileUtil.upload(file);
                production.setEffectPicture(effectPicture);
            }
            if (iProductionDao.saveProduction(production) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除项目
     * <p>
     * 先删除数据库的项目，然后再删除文件
     *
     * @param name
     * @return
     */
    public boolean deleteProduction(String name) {
        Production production = getProductionByName(name);
        if (production == null) {
            throw new ErrorException("项目不存在");
        } else {
            if (iProductionDao.deleteProduction(name) == 1) {
                String effectPicture = production.getEffectPicture();
                if (effectPicture != null) {
                    fileUtil.delete(effectPicture);
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 批量删除项目
     *
     * @param names
     * @return
     */

    public boolean deleteProductions(List<String> names) {
        List<Production> productions = new ArrayList<Production>();

        for (String name : names) {
            Production production = getProductionByName(name);
            if (production == null) {
                throw new ErrorException(name + "项目不存在");
            }
            productions.add(production);
        }
        if (iProductionDao.deleteProductions(names) >= 1) {
            for (Production production : productions) {
                String effectPicture = production.getEffectPicture();
                if (effectPicture != null) {
                    fileUtil.delete(effectPicture);
                }
            }
            return true;
        }
        return false;

    }


    /**
     * 通过名字查询项目
     *
     * @param page
     * @return
     */

    public List<Production> listProductionsByName(Page page) {
        return iProductionDao.listProductionsByName(page);
    }

    /**
     * list所有项目
     *
     * @param page
     * @return
     */
    public List<Production> listProductions(Page page) {
        return iProductionDao.listProductions(page);
    }

    public boolean updateProduction(MultipartFile file, Production production, String originalName) {


        if (production.getName() == null) {
            throw new ErrorException("名字不为空");
        }

        //判断是否修改名字

        Production originalProduction = getProductionByName(originalName);

        if (!originalName.equals(production.getName())) {
            Production currentProduction = getProductionByName(production.getName());
            if (currentProduction != null) {
                throw new ErrorException("你所要修改的学号已经存在");
            }
        }

        int id = originalProduction.getId();
        if (id != 0)
            production.setId(id);
        System.out.println(production.getId());
        //保存文件
        String effectPictureAddress;
        boolean isDeleteEffectPicture = false;
        if (!file.isEmpty()) {
            effectPictureAddress = fileUtil.upload(file);
            production.setEffectPicture(effectPictureAddress);
            isDeleteEffectPicture = true;
        }
        if (iProductionDao.updateProduction(production) == 1) {
            if (isDeleteEffectPicture) {
                fileUtil.delete(originalProduction.getEffectPicture());
            }
            return true;
        }
        return false;
    }
}
