package com.punchthebag.mjtgbot.request;

import java.util.List;

public class SendInlineMessageRequest {

    private String inline_query_id;
    private List<InlineQueryResult> results;

    public String getInline_query_id() {
        return inline_query_id;
    }

    public void setInline_query_id(String inline_query_id) {
        this.inline_query_id = inline_query_id;
    }

    public List<InlineQueryResult> getResults() {
        return results;
    }

    public void setResults(List<InlineQueryResult> results) {
        this.results = results;
    }

}
