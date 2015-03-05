/*
 * Copyright (C) 2015 Two Toasters
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.twotoasters.jazzylistview.sample;

import android.content.Context;
import android.view.Menu;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private Utils() { }

    public static Map<String, Integer> buildEffectMap(Context context) {
        Map<String, Integer> effectMap = new LinkedHashMap<>();
        int i = 0;
        String[] effects = context.getResources().getStringArray(R.array.jazzy_effects);
        for (String effect : effects) {
            effectMap.put(effect, i++);
        }
        return effectMap;
    }

    public static void populateEffectMenu(Menu menu, List<String> effectNames, Context context) {
        Collections.sort(effectNames);
        effectNames.remove(context.getString(R.string.standard));
        effectNames.add(0, context.getString(R.string.standard));
        for (String effectName : effectNames) {
            menu.add(effectName);
        }
    }

    public static int getBackgroundColorRes(int position, int itemLayoutRes) {
        if (itemLayoutRes == R.layout.item) {
            return position % 2 == 0 ? R.color.even : R.color.odd;
        } else {
            return (position % 4 == 0 || position % 4 == 3) ? R.color.even : R.color.odd;
        }
    }
}
