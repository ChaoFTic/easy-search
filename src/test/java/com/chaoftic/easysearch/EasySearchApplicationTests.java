package com.chaoftic.easysearch;

import com.chaoftic.easysearch.db.dao.BloggerDao;
import com.chaoftic.easysearch.db.dao.PostDao;
import com.chaoftic.easysearch.db.models.Blogger;
import com.chaoftic.easysearch.db.models.Post;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasySearchApplicationTests {
    @Autowired
    private BloggerDao bloggerDao;
    @Autowired
    private PostDao postDao;

    @Test
    public void contextLoads() {
    }



    @Test
    public void InsetData() throws DocumentException, ParseException {

        File dir = new File("D:/some_blog");
        File[] files = dir.listFiles();
        for(File file: Objects.requireNonNull(files)) {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);

            String docName = document.getName();
            String realName = docName.substring(docName.lastIndexOf('/')+1);
            String[] split = realName.split("\\.");
            int id = Integer.parseInt(split[0]);
            String gender = split[1];
            int age = Integer.parseInt(split[2]);
            String industry = split[3];
            String astrological_sign = split[4];
            Blogger blogger = new Blogger();
            blogger.setId(id);
            blogger.setAge(age);
            blogger.setAstrologicalSign(astrological_sign);
            blogger.setGender(gender);
            blogger.setIndustry(industry);
            bloggerDao.save(blogger);
            Element rootNode = document.getRootElement();
            List dateList = rootNode.elements("date");
            List postList = rootNode.elements("post");
            for(int i = 0;i < dateList.size();++i){
                Element date = (Element) dateList.get(i);
                Element content =(Element) postList.get(i);
                String dateValue = date.getTextTrim();
                String contentValue = content.getTextTrim();
                Post post = new Post();
                post.setBloggerByBloggerId(blogger);
                post.setContent(contentValue);
                SimpleDateFormat formatter = new SimpleDateFormat("dd,MMM,yyyy", Locale.ENGLISH);
                long timestamp = formatter.parse(dateValue).getTime();
                post.setDate(new Date(timestamp));



                postDao.save(post);
            }

        }
    }
}
