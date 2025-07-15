package com.uchiha.mcparxivassistant.tools;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArxivSearchTool {

    private static final String API_URL = "https://export.arxiv.org/api/query";

    @Tool(description = "Search papers from arXiv by keyword")
    public String searchArxivPapers(@ToolParam(description = "Search keyword") String keyword) {
        try {
            List<String> results = search(keyword);
            return String.join("\n\n", results);
        } catch (Exception e) {
            return "Error searching arXiv: " + e.getMessage();
        }
    }

    public List<String> search(String keyword) {
        // 拼接查询 URL
        String url = API_URL + "?search_query=all:" + keyword + "&start=0&max_results=5";

        // 发送 GET 请求
        String xml = HttpUtil.get(url);

        // Atom XML 转 JSON（Hutool XML转JSON）
        JSONObject json = XML.toJSONObject(xml);

        // 解析
        List<String> paperList = new ArrayList<>();
        JSONObject feed = json.getJSONObject("feed");
        Object entries = feed.get("entry");

        if (entries instanceof JSONArray) {
            for (Object entryObj : (JSONArray) entries) {
                JSONObject entry = (JSONObject) entryObj;
                paperList.add(parseEntry(entry));
            }
        } else if (entries instanceof JSONObject) {
            paperList.add(parseEntry((JSONObject) entries));
        }

        return paperList;
    }

    private String parseEntry(JSONObject entry) {
        String title = entry.getStr("title").replaceAll("\\s+", " ").trim();
        String summary = entry.getStr("summary").replaceAll("\\s+", " ").trim();
        String id = entry.getStr("id").trim();

        return "📄 *" + title + "*\n"
                + "🔗 " + id + "\n"
                + "📝 " + summary;
    }
}

