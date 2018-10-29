package com.chaoftic.easysearch.index;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.index.document.DocItem;
import com.chaoftic.easysearch.index.document.Document;
import com.chaoftic.easysearch.index.document.QueryDocument;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.util.*;

public class Segment {

    public Set<Document> enCut(Collection<BaseEntity> entities) {
        Set<Document> result = new HashSet<>();

        for (BaseEntity entity : entities) {
            try {
                result.add(enCut(entity));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Document enCut(BaseEntity entity) throws IOException {
        Document document = new Document();
        document.setId(entity.getId());

        Analyzer analyzer = new StandardAnalyzer();
        Gson gson = new Gson();

        JsonObject jsonObject = gson.toJsonTree(entity).getAsJsonObject();
        Map<String, String> parsed = parseJson(jsonObject);



        for (Map.Entry<String,String> entry: parsed.entrySet()) {
            if (entry.getKey().equals("date") || entry.getKey().equals("id")){
                continue;
            }
            TokenStream tokenStream = analyzer.tokenStream(entry.getKey(), entry.getValue());
            CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
            OffsetAttribute offsetAttr = tokenStream.addAttribute(OffsetAttribute.class);

            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String word = String.valueOf(charTermAttr);
                DocItem docItem = new DocItem(document.getId(), offsetAttr.startOffset());
                document.add(word, docItem);
            }
            tokenStream.close();
        }


        return document;
    }

    /**
     * 解析JsonObject 取出JsonObject中的所有键值对
     * @param jsonObject JsonObject
     * @return Map of key-value
     */
    private Map<String, String> parseJson(JsonObject jsonObject) {
        Map<String, String> result = new HashMap<>();
        for(String key: jsonObject.keySet()){
            JsonElement value = jsonObject.get(key);
            if (value.isJsonArray() || value.isJsonObject()) {
                result.putAll(parseJson(value.getAsJsonObject()));
            } else {
                result.put(key, value.getAsString());
            }
        }
        return result;
    }

    public QueryDocument enCut(String text) throws IOException {
        QueryDocument document = new QueryDocument();
        document.setId(-1);
        Analyzer analyzer = new StandardAnalyzer();

        TokenStream tokenStream = analyzer.tokenStream("", text);
        CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsetAttr = tokenStream.addAttribute(OffsetAttribute.class);

        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            String word = String.valueOf(charTermAttr);
            DocItem docItem = new DocItem(document.getId(), offsetAttr.startOffset());
            document.add(word, docItem);
        }
        return document;
    }

}
