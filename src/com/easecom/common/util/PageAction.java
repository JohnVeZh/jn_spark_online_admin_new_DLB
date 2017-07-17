/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */


package com.easecom.common.util;

import java.util.HashMap;

public class PageAction {
    /***/
    public static final int FIRST_INT = 0;
    public static final int PREVIOUS_INT = 1;
    public static final int NEXT_INT=2;
    public static final int LAST_INT=3;
    public static final int JUMP_INT=4;
    public static final int CURRENT_INT=5;
    public static final PageAction FIRST = new PageAction(FIRST_INT);
    public static final PageAction PREVIOUS = new PageAction(PREVIOUS_INT);
    public static final PageAction NEXT = new PageAction(NEXT_INT);
    public static final PageAction LAST = new PageAction(LAST_INT);
    public static final PageAction JUMP = new PageAction(JUMP_INT);
    public static final PageAction CURRENT = new PageAction(CURRENT_INT);

    private static final String[] TAGS = {
        "FIRST",
        "PREVIOUS",
        "NEXT",
        "LAST",
        "JUMP",
            "CURRENT"
    };

    private static final PageAction[] VALUES = {
        FIRST,
        PREVIOUS,
        NEXT,
        LAST,
        JUMP,
            CURRENT
    };
    private static final HashMap tagMap = new HashMap();

    private final int value;

    static {
      for (int i = 0; i < TAGS.length; i++) {
        tagMap.put(TAGS[i], VALUES[i]);
      }
    }

    public static PageAction fromString(String tag) {
      PageAction pageAction = (PageAction) tagMap.get(tag.toUpperCase());
      if (pageAction == null && tag != null) {
        throw new IllegalArgumentException(tag);
      }
      return pageAction;
    }

    private PageAction(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }

    public String toString() {
      return TAGS[this.value];
    }

    public Object readResolve() {
      return VALUES[this.value];
  }
}
