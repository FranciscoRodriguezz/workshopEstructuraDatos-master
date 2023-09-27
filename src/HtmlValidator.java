import java.util.Queue;
import java.util.Stack;



public class HtmlValidator {

	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> stack = new Stack<>();

		for (HtmlTag tag : tags) {
			if (tag.isSelfClosing()) {
				continue;
			}

			if (tag.isOpenTag()) {
				stack.push(tag);
			} else {
				if (stack.isEmpty()) {
					return null; // Cierre sin etiqueta de apertura correspondiente
				}

				if (stack.peek().matches(tag)) {
					stack.pop();
				} else {
					return stack; // Etiqueta de cierre que no coincide con la etiqueta de apertura
				}
			}
		}

		if (!stack.isEmpty()) {
			return stack; // Etiquetas de apertura no cerradas
		}

		return new Stack<>(); // HTML válido
	}
}








