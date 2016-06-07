import com.bk.chenxi.biz.EntityBo;
import com.bk.chenxi.dal.mapper.EntityDoMapperExt;
import com.bk.chenxi.dal.model.EntityDo;
import com.bk.chenxi.dto.EntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by wb-yaobingke on 2016/5/19.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@ContextConfiguration(locations={"classpath*:/config/applicationContext.xml"})
public class EntityBoTest extends AbstractTestNGSpringContextTests {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EntityBo entityBo;

    @Autowired
    private EntityDoMapperExt entityDoMapperExt;

    private Integer coffee_entity_id;

    @BeforeClass
    public void beforeClass(){
        logger.info("beforeClass");
        entityDoMapperExt.deletePhysicallyByCreator("test_bk");
        EntityDo entity = new EntityDo();
        entity.setCreator("test_bk");
        entity.setDescription("desc");
        entity.setEntityKey("coffee");
        entity.setName("name");
        entity.setAppId(1);
        entityDoMapperExt.insertSelective(entity);
        this.coffee_entity_id = entity.getId();
        addChildren(entity.getId());
    }

    private void addChildren(Integer parentId){
        EntityDo entity = new EntityDo();
        entity.setCreator("test_bk");
        entity.setDescription("desc");
        entity.setEntityKey("lanshan");
        entity.setName("name");
        entity.setAppId(1);
        entity.setParent(parentId);
        entityDoMapperExt.insertSelective(entity);
        entity.setId(null);
        entity.setEntityKey("owl");
        entityDoMapperExt.insertSelective(entity);
    }

    /**
     * 查找子实体
     */
    @Test
    public void findChildrenEntitys() throws IllegalArgumentException {
            List<EntityDto> list = entityBo.findChildrenEntitys(this.coffee_entity_id);
            assert list!=null && list.size()==2;
    }

    /**
     * 新增实体key已存在
     */
    @Test(expectedExceptions = {IllegalArgumentException.class},expectedExceptionsMessageRegExp = "实体key已存在")
    public void addEntity_uniqueKey() throws IllegalArgumentException {
        try {
            EntityDto entity = new EntityDto("test_addEntity_uniqueKey", "咖啡", "coffee", "coffee is a cool drink", null,1);
            entityBo.addEntity(entity);
        }finally {
            entityDoMapperExt.deletePhysicallyByCreator("test_addEntity_uniqueKey");
        }
    }

    /**
     * 新增实体key已存在
     */
    @Test(expectedExceptions = {IllegalArgumentException.class},expectedExceptionsMessageRegExp = "没有找到指定的父实体")
    public void addEntity_parentExist() throws IllegalArgumentException {
        try {
            EntityDto entity = new EntityDto("test_addEntity_parentExist", "兰尼斯特", "lannister", "lannister", 123456,1);
            entityBo.addEntity(entity);
        }finally {
            entityDoMapperExt.deletePhysicallyByCreator("test_addEntity_parentExist");
        }
    }

    /**
     * 新增实体key非空
     */
    @Test(expectedExceptions = {IllegalArgumentException.class},expectedExceptionsMessageRegExp = "实体key空白")
    public void addEntity_NotNull() throws IllegalArgumentException {
        try{
            EntityDto entity = new EntityDto("test_addEntity_NotNull", "咖啡", null, "coffee is a cool drink", null,1);
            entityBo.addEntity(entity);
        }finally {
            entityDoMapperExt.deletePhysicallyByCreator("test_addEntity_NotNull");
        }
    }

    @AfterClass
    public void afterClass(){
        logger.info("AfterClass");
        entityDoMapperExt.deletePhysicallyByCreator("test_bk");
    }
}
