package guru.qa;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SimpleCallback implements BeforeEachCallback, AfterEachCallback {

    private String key = "str";

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (context.getDisplayName().contains("JDI")) {
            context.getStore(ExtensionContext.Namespace.create("123"))
                    .put(key, "JDI");
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println(context.getStore(ExtensionContext.Namespace.create("123"))
                .get(key));
    }
}
