package com.bk.chenxi.dal.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.PropertyRegistry;

public class GenMapperPlugin extends PluginAdapter {

    private static String FULLY_QUALIFIED_PAGE       = "Page";

    private static String XMLFILE_POSTFIX            = "Ext";

    private static String JAVAFILE_POTFIX            = "Ext";

    private static String SQLMAP_COMMON_POTFIX       = "and is_deleted = 'n'";

    private static String SQLMAP_COMMON_POTFIX_PVG   = "and full_org_path like CONCAT(#{fullOrgPath}, '%')";

    private static String SQLMAP_COMMON_POTFIX_OWNER = "and owner =#{owner,jdbcType=VARCHAR}";

    private static String ANNOTATION_RESOURCE        = "javax.annotation.Resource";

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 增加翻页
        // addLimit(topLevelClass, introspectedTable, "limitStart");
        // addLimit(topLevelClass, introspectedTable, "limitEnd");
        addPage(topLevelClass, introspectedTable, "page");
        addStringField(topLevelClass, introspectedTable, "fullOrgPath");
        addStringField(topLevelClass, introspectedTable, "owner");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 在XXExample对象里添加Page对象属性
     * 
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new org.mybatis.generator.api.dom.java.Parameter(
                                                                             new FullyQualifiedJavaType(
                                                                                                        FULLY_QUALIFIED_PAGE),
                                                                             name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(FULLY_QUALIFIED_PAGE));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * 在XXExample对象里添加StringFiled对象属性
     * 
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addStringField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.lang.String"));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType("java.lang.String"));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    // 添删改Document的sql语句及属性
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        XmlElement parentElement = document.getRootElement();

        updateDocumentNameSpace(introspectedTable, parentElement);

        moveDocumentInsertSql(parentElement);

        updateDocumentInsertSelective(parentElement);

        moveDocumentUpdateByPrimaryKeySql(parentElement);

        generateMysqlPageSql(parentElement, introspectedTable);

        generateDataAccessSql(parentElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void generateMysqlPageSql(XmlElement parentElement, IntrospectedTable introspectedTable) {
        // oracle分页语句前半部分
        String tableName = introspectedTable.getTableConfiguration().getTableName();
        XmlElement paginationPrefixElement = new org.mybatis.generator.api.dom.xml.XmlElement("sql");
        context.getCommentGenerator().addComment(paginationPrefixElement);
        paginationPrefixElement.addAttribute(new Attribute("id", "MysqlDialectPrefix"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "page != null"));
        pageStart.addElement(new org.mybatis.generator.api.dom.xml.TextElement(
                                                                               "from "
                                                                                       + tableName
                                                                                       + " where id in ( select id from ( select id "));
        paginationPrefixElement.addElement(pageStart);
        parentElement.addElement(paginationPrefixElement);

        // oracle分页语句后半部分
        XmlElement paginationSuffixElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(paginationSuffixElement);
        paginationSuffixElement.addAttribute(new Attribute("id", "MysqlDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "page != null"));
        pageEnd.addElement(new TextElement("<![CDATA[ limit #{page.begin}, #{page.length} ) as temp_page_table) ]]>"));
        paginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(paginationSuffixElement);
    }

    private void generateDataAccessSql(XmlElement parentElement) {

        XmlElement fullOrgPathElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(fullOrgPathElement);
        fullOrgPathElement.addAttribute(new Attribute("id", "fullOrgPath"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "fullOrgPath != null"));
        pageStart.addElement(new TextElement(SQLMAP_COMMON_POTFIX_PVG));
        fullOrgPathElement.addElement(pageStart);
        parentElement.addElement(fullOrgPathElement);

        XmlElement ownerElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(ownerElement);
        ownerElement.addAttribute(new Attribute("id", "owner"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "owner != null"));
        pageEnd.addElement(new TextElement(SQLMAP_COMMON_POTFIX_OWNER));
        ownerElement.addElement(pageEnd);
        parentElement.addElement(ownerElement);
    }

    private void moveDocumentUpdateByPrimaryKeySql(XmlElement parentElement) {
        XmlElement updateElement = null;
        for (Element element : parentElement.getElements()) {
            XmlElement xmlElement = (XmlElement) element;
            if (xmlElement.getName().equals("update")) {
                for (Attribute attribute : xmlElement.getAttributes()) {
                    if (attribute.getValue().equals("updateByPrimaryKey")) {
                        updateElement = xmlElement;
                        break;
                    }
                }

            }

        }
        parentElement.getElements().remove(updateElement);
    }

    private void updateDocumentInsertSelective(XmlElement parentElement) {
        XmlElement oldElement = null;
        XmlElement newElement = null;
        for (Element element : parentElement.getElements()) {
            XmlElement xmlElement = (XmlElement) element;
            if (xmlElement.getName().equals("insert")) {
                for (Attribute attribute : xmlElement.getAttributes()) {
                    if (attribute.getValue().equals("insertSelective")) {
                        oldElement = xmlElement;
                        newElement = xmlElement;
                        newElement.addAttribute(new Attribute("useGeneratedKeys", "true"));
                        newElement.addAttribute(new Attribute("keyProperty", "id"));
                        break;
                    }
                }
            }
        }
        parentElement.getElements().remove(oldElement);
        parentElement.getElements().add(newElement);
    }

    private void moveDocumentInsertSql(XmlElement parentElement) {
        XmlElement insertElement = null;
        for (Element element : parentElement.getElements()) {
            XmlElement xmlElement = (XmlElement) element;
            if (xmlElement.getName().equals("insert")) {
                for (Attribute attribute : xmlElement.getAttributes()) {
                    if (attribute.getValue().equals("insert")) {
                        insertElement = xmlElement;
                        break;
                    }
                }
            }
        }
        parentElement.getElements().remove(insertElement);
    }

    private void updateDocumentNameSpace(IntrospectedTable introspectedTable, XmlElement parentElement) {
        Attribute namespaceAttribute = null;
        for (Attribute attribute : parentElement.getAttributes()) {
            if (attribute.getName().equals("namespace")) {
                namespaceAttribute = attribute;
            }
        }
        parentElement.getAttributes().remove(namespaceAttribute);
        parentElement.getAttributes().add(new Attribute("namespace", introspectedTable.getMyBatis3JavaMapperType()
                                                                     + JAVAFILE_POTFIX));
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        TextElement text = new TextElement(SQLMAP_COMMON_POTFIX);
        element.addElement(text);
        return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();
        XmlElement setItem = null;
        int modifierItemIndex = -1;
        int gmtModifiedItemIndex = -1;
        for (Element e : elements) {
            if (e instanceof XmlElement) {
                setItem = (XmlElement) e;
                for (int i = 0; i < setItem.getElements().size(); i++) {
                    XmlElement xmlElement = (XmlElement) setItem.getElements().get(i);
                    for (Attribute att : xmlElement.getAttributes()) {
                        if (att.getValue().equals("modifier != null")) {
                            modifierItemIndex = i;
                            break;
                        }

                        if (att.getValue().equals("gmtModified != null")) {
                            gmtModifiedItemIndex = i;
                            break;
                        }
                    }
                }
            }
        }

        if (modifierItemIndex != -1 && setItem != null) {
            addXmlElementModifier(setItem, modifierItemIndex);
        }

        if (gmtModifiedItemIndex != -1 && setItem != null) {
            addGmtModifiedXmlElement(setItem, gmtModifiedItemIndex);
        }

        TextElement text = new TextElement(SQLMAP_COMMON_POTFIX);
        element.addElement(text);
        return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
    }

    private void addGmtModifiedXmlElement(XmlElement setItem, int gmtModifiedItemIndex) {
        XmlElement defaultGmtModified = new XmlElement("if");
        defaultGmtModified.addAttribute(new Attribute("test", "gmtModified == null"));
        defaultGmtModified.addElement(new TextElement("GMT_MODIFIED = current_timestamp,"));
        setItem.getElements().add(gmtModifiedItemIndex + 1, defaultGmtModified);
    }

    private void addXmlElementModifier(XmlElement setItem, int modifierItemIndex) {
        XmlElement defaultmodifier = new XmlElement("if");
        defaultmodifier.addAttribute(new Attribute("test", "modifier == null"));
        defaultmodifier.addElement(new TextElement("MODIFIER = 'system',"));
        setItem.getElements().add(modifierItemIndex + 1, defaultmodifier);
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
                                                                        IntrospectedTable introspectedTable) {
        TextElement text = new TextElement(SQLMAP_COMMON_POTFIX);
        element.addElement(text);
        return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        element.setName("update");
        int replaceInd = -1;
        for (int i = 0; i < element.getAttributes().size(); i++) {
            Attribute attr = element.getAttributes().get(i);
            if (attr.getName().equals("parameterType")) {
                replaceInd = i;
                break;
            }
        }
        if (replaceInd >= 0) {
            element.getAttributes().remove(replaceInd);
            element.getAttributes().add(replaceInd,
                                        new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        }
        List<Element> removeElement = new ArrayList<Element>();
        for (int i = 5; i < element.getElements().size(); i++) {
            removeElement.add(element.getElements().get(i));

        }
        element.getElements().removeAll(removeElement);
        element.getElements().add(new TextElement(
                                                  "update "
                                                          + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()
                                                          + " set is_deleted = 'y',modifier=#{modifier,jdbcType=VARCHAR},gmt_Modified=current_timestamp where ID = #{id,jdbcType=BIGINT}"));
        return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    // insert
    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();
        XmlElement fieldItem = null;
        XmlElement valueItem = null;
        for (Element e : elements) {
            if (e instanceof XmlElement) {
                XmlElement xmlElement = (XmlElement) e;
                if (xmlElement.getName().equals("trim")) {
                    for (Attribute arr : xmlElement.getAttributes()) {
                        if (arr.getValue().equals("(")) {
                            fieldItem = xmlElement;
                            break;
                        }
                        if (arr.getValue().equals("values (")) {
                            valueItem = xmlElement;
                            break;
                        }
                    }
                }
            }
        }

        if (fieldItem != null) {
            XmlElement defaultGmtCreate = new XmlElement("if");
            defaultGmtCreate.addAttribute(new Attribute("test", "gmtCreate == null"));
            defaultGmtCreate.addElement(new TextElement("GMT_CREATE,"));
            fieldItem.addElement(1, defaultGmtCreate);

            XmlElement defaultGmtModified = new XmlElement("if");
            defaultGmtModified.addAttribute(new Attribute("test", "gmtModified == null"));
            defaultGmtModified.addElement(new TextElement("GMT_MODIFIED,"));
            fieldItem.addElement(1, defaultGmtModified);

            XmlElement defaultmodifier = new XmlElement("if");
            defaultmodifier.addAttribute(new Attribute("test", "modifier == null"));
            defaultmodifier.addElement(new TextElement("MODIFIER,"));
            fieldItem.addElement(1, defaultmodifier);

            XmlElement defaultCreator = new XmlElement("if");
            defaultCreator.addAttribute(new Attribute("test", "creator == null"));
            defaultCreator.addElement(new TextElement("CREATOR,"));
            fieldItem.addElement(1, defaultCreator);

            XmlElement defaultIsDeleted = new XmlElement("if");
            defaultIsDeleted.addAttribute(new Attribute("test", "isDeleted == null"));
            defaultIsDeleted.addElement(new TextElement("IS_DELETED,"));
            fieldItem.addElement(1, defaultIsDeleted);
        }

        if (valueItem != null) {
            XmlElement defaultGmtCreate = new XmlElement("if");
            defaultGmtCreate.addAttribute(new Attribute("test", "gmtCreate == null"));
            defaultGmtCreate.addElement(new TextElement("current_timestamp,"));
            valueItem.addElement(1, defaultGmtCreate);

            XmlElement defaultGmtModified = new XmlElement("if");
            defaultGmtModified.addAttribute(new Attribute("test", "gmtModified == null"));
            defaultGmtModified.addElement(new TextElement("current_timestamp,"));
            valueItem.addElement(1, defaultGmtModified);

            XmlElement defaultmodifier = new XmlElement("if");
            defaultmodifier.addAttribute(new Attribute("test", "modifier == null"));
            defaultmodifier.addElement(new TextElement("'system',"));
            valueItem.addElement(1, defaultmodifier);

            XmlElement defaultCreator = new XmlElement("if");
            defaultCreator.addAttribute(new Attribute("test", "creator == null"));
            defaultCreator.addElement(new TextElement("'system',"));
            valueItem.addElement(1, defaultCreator);

            XmlElement defaultIsDeleted = new XmlElement("if");
            defaultIsDeleted.addAttribute(new Attribute("test", "isDeleted == null"));
            defaultIsDeleted.addElement(new TextElement("'n',"));
            valueItem.addElement(1, defaultIsDeleted);
        }

        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
                                                           IntrospectedTable introspectedTable) {
        Parameter parameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record");
        method.getParameters().clear();
        method.addParameter(parameter);
        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        List<Method> methods = interfaze.getMethods();
        Method insertMethod = null;
        for (Method method : methods) {
            if (method.getName().equals("insert")) {
                insertMethod = method;
                break;
            }
        }
        methods.remove(insertMethod);

        Method updateMethod = null;
        for (Method method : methods) {
            if (method.getName().equals("updateByPrimaryKey")) {
                updateMethod = method;
                break;
            }
        }
        methods.remove(updateMethod);

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    // selectByExample
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        XmlElement lastXmlE = (XmlElement) element.getElements().remove(element.getElements().size() - 1);
        XmlElement pageStart = new XmlElement("include");
        pageStart.addAttribute(new Attribute("refid", "MysqlDialectPrefix"));
        element.getElements().add(8, pageStart);
        XmlElement isdeletedElement = new XmlElement("if");
        isdeletedElement.addAttribute(new Attribute("test", "oredCriteria.size != 0"));
        isdeletedElement.addElement(new TextElement(SQLMAP_COMMON_POTFIX));
        element.addElement(isdeletedElement);
        isdeletedElement = new XmlElement("if");
        isdeletedElement.addAttribute(new Attribute("test", "oredCriteria.size == 0"));
        isdeletedElement.addElement(new TextElement("where is_deleted = 'n'"));
        element.addElement(isdeletedElement);

        XmlElement fullOrgPath = new XmlElement("include");
        fullOrgPath.addAttribute(new Attribute("refid", "fullOrgPath"));
        element.addElement(fullOrgPath);

        XmlElement owner = new XmlElement("include");
        owner.addAttribute(new Attribute("refid", "owner"));
        element.addElement(owner);

        element.addElement(lastXmlE);
        XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$     
        isNotNullElement.addAttribute(new Attribute("refid", "MysqlDialectSuffix"));
        element.getElements().add(isNotNullElement);

        XmlElement orderByElement = new XmlElement("if");
        orderByElement.addAttribute(new Attribute("test", "orderByClause != null"));
        orderByElement.addElement(new TextElement("order by ${orderByClause}"));
        element.getElements().add(orderByElement);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        // 添加is_deleted = 'n'
        XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$  
        isNotNullElement.addAttribute(new Attribute("test", "oredCriteria.size != 0")); //$NON-NLS-1$ //$NON-NLS-2$  
        isNotNullElement.addElement(new TextElement(SQLMAP_COMMON_POTFIX));
        element.addElement(isNotNullElement);
        isNotNullElement = new XmlElement("if"); //$NON-NLS-1$  
        isNotNullElement.addAttribute(new Attribute("test", "oredCriteria.size == 0")); //$NON-NLS-1$ //$NON-NLS-2$  
        isNotNullElement.addElement(new TextElement("where is_deleted = 'n'"));
        element.addElement(isNotNullElement);
        XmlElement fullOrgPath = new XmlElement("include");
        fullOrgPath.addAttribute(new Attribute("refid", "fullOrgPath"));
        element.addElement(fullOrgPath);
        // 添加
        XmlElement owner = new XmlElement("include");
        owner.addAttribute(new Attribute("refid", "owner"));
        element.addElement(owner);
        return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
    }

    // 生成XXExt.xml
    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

        String[] splitFile = introspectedTable.getMyBatis3XmlMapperFileName().split("\\.");
        String fileNameExt = null;
        if (splitFile[0] != null) {
            fileNameExt = splitFile[0] + XMLFILE_POSTFIX + ".xml";
        }

        if (isExistExtFile(context.getSqlMapGeneratorConfiguration().getTargetProject(),
                           introspectedTable.getMyBatis3XmlMapperPackage(), fileNameExt)) {
            return super.contextGenerateAdditionalXmlFiles(introspectedTable);
        }

        Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);

        XmlElement root = new XmlElement("mapper");
        document.setRootElement(root);
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace() + XMLFILE_POSTFIX;
        root.addAttribute(new Attribute("namespace", namespace));

        GeneratedXmlFile gxf = new GeneratedXmlFile(document, fileNameExt,
                                                    introspectedTable.getMyBatis3XmlMapperPackage(),
                                                    context.getSqlMapGeneratorConfiguration().getTargetProject(),
                                                    false, context.getXmlFormatter());

        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>(1);
        answer.add(gxf);

        return answer;
    }

    // 生成XXExt.java
    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType()
                                                                 + JAVAFILE_POTFIX);
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        context.getCommentGenerator().addJavaFileComment(interfaze);

        FullyQualifiedJavaType baseInterfaze = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        interfaze.addSuperInterface(baseInterfaze);

        FullyQualifiedJavaType annotation = new FullyQualifiedJavaType(ANNOTATION_RESOURCE);
        interfaze.addAnnotation("@Resource");
        interfaze.addImportedType(annotation);

        CompilationUnit compilationUnits = interfaze;
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(
                                                                    compilationUnits,
                                                                    context.getJavaModelGeneratorConfiguration().getTargetProject(),
                                                                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                                                                    context.getJavaFormatter());

        if (isExistExtFile(generatedJavaFile.getTargetProject(), generatedJavaFile.getTargetPackage(),
                           generatedJavaFile.getFileName())) {
            return super.contextGenerateAdditionalJavaFiles(introspectedTable);
        }
        List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<GeneratedJavaFile>(1);
        generatedJavaFile.getFileName();
        generatedJavaFiles.add(generatedJavaFile);
        return generatedJavaFiles;
    }

    private boolean isExistExtFile(String targetProject, String targetPackage, String fileName) {

        File project = new File(targetProject);
        if (!project.isDirectory()) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, ".");
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                return true;
            }
        }

        File testFile = new File(directory, fileName);
        if (testFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This plugin is always valid - no properties are required
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    public static void main(String[] args) {
        String config = GenMapperPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
        String[] arg = { "-configfile", config };
        ShellRunner.main(arg);
    }
}
