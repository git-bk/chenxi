import com.bk.chenxi.biz.EntityBo;
import com.bk.chenxi.biz.FieldBo;
import com.bk.chenxi.dal.mapper.EntityDoMapperExt;
import com.bk.chenxi.dal.mapper.FieldDoMapperExt;
import com.bk.chenxi.dal.model.EntityDo;
import com.bk.chenxi.dto.FieldDto;
import com.bk.chenxi.enums.YesOrNo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wb-yaobingke on 2016/5/19.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@ContextConfiguration(locations={"classpath*:/config/applicationContext.xml"})
public class FieldBoTest extends AbstractTestNGSpringContextTests {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EntityBo entityBo;

    @Autowired
    private FieldBo fieldBo;

    @Autowired
    private EntityDoMapperExt entityDoMapperExt;

    @Autowired
    private FieldDoMapperExt fieldDoMapperExt;
    private Integer coffee_entity_id;

    public static int index;

    public static String creator = "bk_test";

    @BeforeClass
    public void beforeClass(){
        logger.info("beforeClass");
        entityDoMapperExt.deletePhysicallyByCreator(creator);
        EntityDo entity = new EntityDo();
        entity.setCreator(creator);
        entity.setDescription("desc");
        entity.setEntityKey("coffee");
        entity.setName("name");
        entity.setAppId(1);
        entityDoMapperExt.insertSelective(entity);
        this.coffee_entity_id = entity.getId();
    }

    @AfterClass
    public void afterClass(){
        logger.info("AfterClass");
        entityDoMapperExt.deletePhysicallyByCreator(creator);
    }

    @Test
    public void addField(){
        try {
            FieldDto field = newField();
            field.setCreator("test_addField");
            field=fieldBo.addField(field);
            assert StringUtils.equals(field.getColumnName(),"column1");
        }finally {
            fieldDoMapperExt.deletePhysicallyByCreator("test_addField");
        }
    }


    /**
     * 新增字段key已存在
     */
    @Test(expectedExceptions = {IllegalArgumentException.class},expectedExceptionsMessageRegExp = "fieldKey已存在")
    public void addField_uniqueKey() throws IllegalArgumentException {
        try {
            FieldDto field = newField();
        }finally {
            entityDoMapperExt.deletePhysicallyByCreator("test_addEntity_uniqueKey");
        }
    }

    private FieldDto newField(){
        FieldDto field = new FieldDto();
        field.setEntityId(this.coffee_entity_id );
        field.setFieldKey("f_key_"+this.index++);
        field.setName("f_name_"+this.index++);
        field.setCreator(creator);
        field.setFieldType(30);
        field.setDescription("desc");
        field.setDefaultValue("defaultValue");
        field.setFieldOrder(100);
        field.setIsRequired(YesOrNo.NO.getValue());
        return field;
    }




}
