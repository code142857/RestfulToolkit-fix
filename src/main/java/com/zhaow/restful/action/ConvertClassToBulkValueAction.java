package com.zhaow.restful.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.zhaow.restful.common.PsiClassHelper;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.asJava.LightClassUtil;
import org.jetbrains.kotlin.asJava.LightClassUtilsKt;
import org.jetbrains.kotlin.psi.KtClassOrObject;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConvertClassToBulkValueAction extends AbstractBaseAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiElement psiElement = e.getData(CommonDataKeys.PSI_ELEMENT);
        PsiClass psiClass = getPsiClass(psiElement);

        if (psiClass == null) {
            return;
        }

        final List<PsiField> fields = getFields(psiClass);
        if (fields != null && !fields.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (PsiField field : fields) {
                final String fieldName = field.getName();
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                stringBuilder.append(String.format("%s:%s\r\n", fieldName, PsiClassHelper.getJavaBaseTypeDefaultValue(field.getType().getPresentableText())));
            }
            CopyPasteManager.getInstance().setContents(new StringSelection(stringBuilder.toString()));
        }
    }

    protected List<PsiClass> getPsiClassLinkList(PsiClass psiClass) {
        List<PsiClass> psiClassList = new ArrayList<>();
        PsiClass currentClass = psiClass;
        while (null != currentClass && !"Object".equals(currentClass.getName())) {
            psiClassList.add(currentClass);
            currentClass = currentClass.getSuperClass();
        }
        Collections.reverse(psiClassList);
        return psiClassList;
    }

    protected List<PsiField> getFields(PsiClass psiClass) {
        final List<PsiClass> psiClassLinkList = getPsiClassLinkList(psiClass);
        List<PsiField> fields = new ArrayList<>();
        for (PsiClass pc : psiClassLinkList) {
            for (PsiField field : pc.getFields()) {
                fields.add(field);
            }
        }
        return fields;
    }

    @Nullable
    protected PsiClass getPsiClass(PsiElement psiElement) {
        PsiClass psiClass = null;
        if (psiElement instanceof PsiClass) {
            psiClass = (PsiClass) psiElement;

        } else if (psiElement instanceof KtClassOrObject) {
            if (LightClassUtil.INSTANCE.canGenerateLightClass((KtClassOrObject) psiElement)) {
                psiClass = LightClassUtilsKt.toLightClass((KtClassOrObject) psiElement);
            }
        }
        return psiClass;
    }

    @Override
    public void update(AnActionEvent e) {
        PsiElement psiElement = e.getData(CommonDataKeys.PSI_ELEMENT);
        setActionPresentationVisible(e, psiElement instanceof PsiClass || psiElement instanceof KtClassOrObject);
    }
}
