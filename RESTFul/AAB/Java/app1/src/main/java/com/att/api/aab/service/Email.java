/* vim: set expandtab tabstop=4 shiftwidth=4 softtabstop=4: */

/*
 * Copyright 2014 AT&T
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.att.api.aab.service;

import org.json.JSONObject;

public final class Email {
    private final String type;
    private final String emailAddress;
    private final Boolean preferred;

    public Email(String type, String emailAddress, Boolean preferred) {
        this.type = type;
        this.emailAddress = emailAddress;
        this.preferred = preferred;
    }

    public String getType() {
        return type;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public Boolean isPreferred() {
        return preferred;
    }

    public JSONObject toJson() {
        JSONObject jobj = new JSONObject();

        final String[] keys = { "type", "preferred", "emailAddress" };
        String prefString = null;
        if (getPreferred() != null) {
            prefString = getPreferred() ? "TRUE" : "FALSE";
        }
        final String[] values = { getType(), prefString, getEmailAddress() };

        for (int i = 0; i < keys.length; ++i) {
            if (values[i] == null) continue;
            jobj.put(keys[i], values[i]);
        }

        return jobj;
    }

    public static Email valueOf(JSONObject jobj) {
        String type = jobj.has("type") ? jobj.getString("type") : null;
        String emailAddr = jobj.has("emailAddress") ? jobj.getString("emailAddress") : null;
        Boolean pref = jobj.has("preferred") ? jobj.getBoolean("preferred") : null;
        return new Email(type, emailAddr, pref);
    }
}
