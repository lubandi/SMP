/*
 * SicMu Player - Lightweight music player for Android
 * Copyright (C) 2015  Mathieu Souchaud
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package souch.smp;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;

import java.util.HashMap;
import java.util.Map;


public class Row {
    // level from the left
    protected int level;
    // position of the row within the unfolded rows array
    protected int genuinePos;
    protected int typeface;
    // null if no parent
    protected Row parent;

    public Row(int position, int theLevel, int theTypeface) {
        genuinePos = position;
        level = theLevel;
        typeface = theTypeface;
        parent = null;
    }

    public void setGenuinePos(int position) { genuinePos = position; }
    public int getGenuinePos() { return genuinePos; }

    public Row getParent() { return parent; }
    public void setParent(Row parent) { this.parent = parent; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setView(RowViewHolder holder, Main main, int position) {
        holder.layout.setBackgroundColor(Color.argb(0x88, 0x0, 0x0, 0x0));
        holder.text.setPadding(convertDpToPixels(level * 10, holder.layout.getResources()), 0, 0, 0);
        holder.text.setTypeface(null, typeface);
    }

    // cache result
    private static Map<Integer, Integer> converted = new HashMap<>();
    public static int convertDpToPixels(int dp, Resources resources) {
        int px;
        if (converted.containsKey(dp)) {
            px = converted.get(dp);
        }
        else {
            px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    resources.getDisplayMetrics());
            converted.put(dp, px);
        }
        return px;
    }
}
