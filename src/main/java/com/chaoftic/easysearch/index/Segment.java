package com.chaoftic.easysearch.index;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.index.document.Document;
import com.chaoftic.easysearch.index.document.DocItem;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class Segment {

    public static Map<String, DocItem> enCut(String fieldName, String text, Integer docId) throws IOException {
        Map<String, DocItem> result = new HashMap<>();
        Analyzer analyzer = new StandardAnalyzer();

        TokenStream tokenStream = analyzer.tokenStream(fieldName, text);
        CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsetAttr = tokenStream.addAttribute(OffsetAttribute.class);

        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            String word = charTermAttr.toString();
            DocItem docItem = new DocItem(offsetAttr.startOffset());

            if (result.containsKey(word)) {
                result.get(word).merge(docItem);
            } else {
                result.put(word, docItem);
            }
        }
        return result;
    }

    public Set<Document> enCut(Collection<BaseEntity> entities) {
        Set<Document> result = new HashSet<>();

        for (BaseEntity entity : entities) {
            try {
                result.add(enCut(entity));
            } catch (IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Document enCut(BaseEntity entity) throws IllegalAccessException, IOException {
        Document document = new Document();
        document.setId(entity.getId());

        Analyzer analyzer = new StandardAnalyzer();
        for (Field field : entity.getClass().getDeclaredFields()) {

            TokenStream tokenStream = analyzer.tokenStream(field.getName(), String.valueOf(field.get(entity)));
            CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
            OffsetAttribute offsetAttr = tokenStream.addAttribute(OffsetAttribute.class);

            tokenStream.reset();
            while(tokenStream.incrementToken()){
                String word = String.valueOf(charTermAttr);
                DocItem docItem = new DocItem(offsetAttr.startOffset());

                document.add(word, docItem);


            }
        }


        return document;
    }

}
