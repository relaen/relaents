import { RelaenManager } from "./relaenmanager";
import { PlaceholderFactory } from "./placeholderfactory";


class RelaenUtil {
    /**
     * 对象id计数器
     */
    private static idGenerator = 0;

    /**
     * 生成对象id，提供 entitymanager 和connection使用
     */
    public static genId() {
        return ++this.idGenerator;
    }

    /**
     * 处理字段字符串值
     * @param value     待处理值
     * @returns         处理后的字符串
     */
    public static valueToString(value: any): string {
        if (typeof value !== 'string') {
            value = value + '';
        }
        //替换 ' 为 \'
        value = value.replace(/'/g, "\'");
        //两端添加 '
        return "'" + value + "'";
    }

    /**
     * 处理参数占位符
     * @param sql   sql串
     * @returns     修改后的sql
     */
    public static handlePlaceholder(sql: string): string {
        // 占位符为?的默认处理
        if (['mysql', 'mariadb', 'sqlite'].includes(RelaenManager.dialect)) {
            return sql;
        }
        let reg = /(\'.*?\?.*?\')|\?/g;
        let index = 0;

        return sql.replace(reg, (match, p1) => {
            if (match !== '?') {
                return p1;
            }
            return PlaceholderFactory.get(index++);
        });
    }

    /**
     * 字符串转正则表达式
     * @param str       源串
     * @param side      匹配的边 1 左边 2右边 3两边
     * @returns
     */
    private static toReg(str: string, side?: number): RegExp {
        //替换/为\/
        str = str.replace(/\//g, '\\/');
        //替换.为\.
        str = str.replace(/\./g, '\\.');
        //替换*为.*
        str = str.replace(/\*/g, '.*');
        if (side !== undefined) {
            switch (side) {
                case 1:
                    str = '^' + str;
                    break;
                case 2:
                    str = str + '$';
                    break;
                case 3:
                    str = '^' + str + '$';
            }
        }
        return new RegExp(str);
    }
}

export { RelaenUtil }