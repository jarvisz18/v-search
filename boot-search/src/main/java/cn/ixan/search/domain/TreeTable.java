package cn.ixan.search.domain;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/3/1 15:48
 * @description tree table
 */
@Data
public class TreeTable {
    private String id;
    private String name;
    private String parentId;
    private List<TreeTable> children;

    /**
     * <p>表里面要有id，name, parentId这几个字段</p>
     *
     * @param id       父节点id
     * @param rootTree 所有数据的集合
     * @return childList
     */
    private static List<TreeTable> getChild(String id, List<TreeTable> rootTree) {
        List<TreeTable> childList = new ArrayList<>();
        for (TreeTable node : rootTree) {
            if (id.equals(node.getParentId())) {
                childList.add(node);
            }
        }
        for (TreeTable tree : childList) {
            tree.setChildren(getChild(tree.getId(), rootTree));
        }
        if (CollectionUtils.isEmpty(rootTree)) {
            return null;
        }
        return childList;
    }
}
