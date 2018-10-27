package com.chaoftic.easysearch.index.segment;

import com.chaoftic.easysearch.EasySearchApplicationTests;
import com.chaoftic.easysearch.index.inverted.InvertedIndex;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttribute;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;

public class SegmentTest extends EasySearchApplicationTests {

    @Test
    public void EnglistCut() throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        String fieldName = "";
        String text = "At last back to a normal apple company company where it expected that you go to the company at lunch on Fridays. Plus they have free breakfast (quite a spread too!). Good to do a bit of socialising.";

        InvertedIndex index = new InvertedIndex();

        TokenStream tokenStream = analyzer.tokenStream(fieldName, text);
        CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
        TermFrequencyAttribute frequencyAttr = tokenStream.addAttribute(TermFrequencyAttribute.class);
        OffsetAttribute offsetAttr = tokenStream.addAttribute(OffsetAttribute.class);

        tokenStream.reset();
        while (tokenStream.incrementToken()) {

            String word = charTermAttr.toString();
            int frequency = frequencyAttr.getTermFrequency();
            System.out.println(word + " " + frequency);
        }
    }

    @Test
    public void ChineseCut() throws IOException {
//        CharArraySet stopDic = WordlistLoader.getWordSet(IOUtils.getDecodingReader(SmartChineseAnalyzer.class, "stop_words.txt", StandardCharsets.UTF_8), "//");
        Resource resource = new ClassPathResource("data/stop_words.dic");
        FileReader fileReader = new FileReader(resource.getFile());
        CharArraySet stopDic = WordlistLoader.getWordSet(fileReader, "//");
        Analyzer analyzer = new SmartChineseAnalyzer(stopDic);
        String fieldName = "field";
        String text = "学长他就像我生命中的灵感，他让我了解爱的积极意义，他就像是让我一直前进的动力，\n" +
                "\n" +
                "其实这部在内地12年上映的泰国校园爱情小清新电影，每次想到学生时期的那份纯纯爱恋，总是会把它再刷一遍，遍遍的感触都不同，就像那个她永远在我心裡，但却越来越模糊。\n" +
                "\n" +
                "校园+淡淡初恋+死党哥们阋牆+闺密吃醋吵架闹彆扭+成长后回忆的淡淡哀伤与遗憾大致上总结\n" +
                "\n" +
                "故事在惯有的校园学长学妹暗恋开始，小水喜欢同校的那个转学生学长阿亮，她爱偷偷在角落看著他踢足球，为了他去选军乐队指挥，参与话剧演出，去报名舞蹈社团，那麽努力的改变";

        InvertedIndex index = new InvertedIndex();

        TokenStream tokenStream = analyzer.tokenStream(fieldName, text);
        CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
        TermFrequencyAttribute frequencyAttr = tokenStream.addAttribute(TermFrequencyAttribute.class);
        OffsetAttribute offsetAttr = tokenStream.addAttribute(OffsetAttribute.class);

        tokenStream.reset();
        while (tokenStream.incrementToken()) {

            String word = charTermAttr.toString();
            int frequency = frequencyAttr.getTermFrequency();
            System.out.println(word + " " + frequency);
        }
    }
}