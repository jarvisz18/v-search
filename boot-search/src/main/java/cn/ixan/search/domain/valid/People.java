package cn.ixan.search.domain.valid;

import lombok.Data;
import net.sf.jsqlparser.statement.select.First;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/3/1 13:51
 * @description people entity
 */
@Data
public class People {
    /**
     * 在First分组时，判断不能为空
     */
    @NotEmpty(message = "id不能为空", groups = {Insert.class})
    private String id;
    /**
     * name字段不为空，且长度在3-8之间
     */
    @NotEmpty(groups = {First.class})
    @Size(min = 3, max = 8, groups = {Update.class}, message = "name字段不为空,且长度在3-8之间")
    private String name;
}
