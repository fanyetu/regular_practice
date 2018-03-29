package cn.fanyetu.jvm.processor;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;

/**
 * 程序名称规范编译器插件
 * 如果程序命名不符合规范，将会输出一个编译器的warning信息
 *
 * @author zhanghaonan
 * @date 2018/3/29
 */
public class NameChecker {

    private final Messager messager;

    private NameCheckerScanner nameCheckerScanner = new NameCheckerScanner();

    NameChecker(ProcessingEnvironment processingEnvironment) {
        messager = processingEnvironment.getMessager();
    }

    /**
     * 对java程序的命名进行检查
     *
     * @param element
     */
    public void checkNames(Element element) {
        nameCheckerScanner.scan(element);
    }

    /**
     * 名称检查器实现类
     * 使用Visitor模式访问抽象语法树中的元素
     */
    private class NameCheckerScanner extends ElementScanner8<Void, Void> {

        /**
         * 检查java类
         *
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(), aVoid);
            checkCamelCase(e, true);
            super.visitType(e, aVoid);
            return null;
        }

        /**
         * 检查方法
         *
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (ElementKind.METHOD.equals(e.getKind())) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "一个普通方法\"" + name
                            + "\"不应当与类名重复，避免与构造函数产生混淆", e);
                }
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

        /**
         * 检查变量名
         *
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            // 如果这个variable是枚举或者常量，那么按大写命名检查，否则按驼峰命名检查
            if (ElementKind.ENUM_CONSTANT.equals(e.getKind()) || null != e.getConstantValue()
                    || heuristicallyConstant(e)) {
                checkAllCaps(e);
            } else {
                checkCamelCase(e, false);
            }
            return null;
        }

        /**
         * 判断一个变量是否是常量
         *
         * @param e
         * @return
         */
        private boolean heuristicallyConstant(VariableElement e) {
            if (ElementKind.INTERFACE.equals(e.getEnclosingElement().getKind())) {
                return true;
            }
            if (ElementKind.FIELD.equals(e.getKind()) &&
                    e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                return true;
            }
            return false;
        }

        /**
         * 大写命名检查
         *
         * @param e
         */
        private void checkAllCaps(Element e) {
            String name = e.getSimpleName().toString();
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (!Character.isUpperCase(firstCodePoint)) {
                conventional = false;
            } else {
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int) '_') {
                        if (previousUnderscore) {
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称\"" + name +
                        "\"应当全部以答谢字母或下划线命名，并且以字母开头", e);
            }
        }

        /**
         * 驼峰命名检查
         *
         * @param e
         * @param initialCaps 首字母是否大写
         */
        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if (Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if (!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称\"" + name + "\"应当以小写字母开头", e);
                    return;
                }
            } else if (Character.isLowerCase(firstCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称\"" + name + "\"应当以大写字母开头", e);
                    return;
                }
            } else {
                conventional = false;
            }

            if (conventional) {
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    } else {
                        previousUpper = false;
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称\"" + name + "\"应当符合驼峰命名法", e);
            }
        }
    }
}
