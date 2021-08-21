package jp.freeapps.intellij.plugin.phparray.action

import com.intellij.json.JsonLanguage
import com.intellij.json.psi.JsonFile
import com.intellij.lang.Language
import com.intellij.psi.PsiFile
import jp.freeapps.intellij.plugin.phparray.converter.JsonConverter

/**
 * Menu action to replace a selection of characters with a php array string.
 *
 * @see BaseAction
 */
class JsonToPhpArrayAction : BaseAction() {
    /**
     * @inheritDoc
     */
    override fun getLanguage(): Language {
        return JsonLanguage.INSTANCE
    }

    /**
     * Replace the selected text with php array string.
     */
    override fun replaceSelectedText(psiFile: PsiFile): String {
        if (psiFile !is JsonFile) return psiFile.text
        return JsonConverter(psiFile).toPhpArray()
    }

    /**
     * Determine if the selected text is a psi file of valid replaceable json text.
     */
    override fun isValid(psiFile: PsiFile): Boolean {
        if (psiFile !is JsonFile) return false
        return JsonConverter(psiFile).isValid()
    }
}