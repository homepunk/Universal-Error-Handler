package github.homepunk.com.fieldserrorhandler.builders;

import android.support.annotation.StringRes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import github.homepunk.com.fieldserrorhandler.handlers.validators.interfaces.ParticularHandler;

/**
 * Created by Homepunk on 21.09.2017.
 **/

public class TargetHandler {
    private final Builder builder;

    private TargetHandler(Builder builder) {
        this.builder = builder;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Set<Integer> getCodes() {
        return builder.codes;
    }

    public Map<Integer, String> getCodeMessages() {
        return builder.getCodeMessages();
    }

    public Map<Integer, Integer> getCodeResMessages() {
        return builder.getCodeResMessages();
    }

    public Map<Integer, ParticularHandler> getValidators() {
        return builder.getValidators();
    }

    public static final class Builder {
        private Set<Integer> codes;
        private Map<Integer, String> codeMessages;
        private Map<Integer, Integer> codeResMessages;
        private Map<Integer, ParticularHandler> validators;

        private Builder() {
            this.codes = new HashSet<>();
            this.validators = new HashMap<>();
            this.codeMessages = new HashMap<>();
            this.codeResMessages = new HashMap<>();
        }

        public Builder add(int code, @StringRes int stringResId, ParticularHandler validator) {
            codes.add(code);
            codeResMessages.put(code, stringResId);
            validators.put(code, validator);
            return this;
        }

        public Builder add(int code, String message, ParticularHandler validator) {
            codes.add(code);
            codeMessages.put(code, message);
            validators.put(code, validator);
            return this;
        }

        public Builder remove(int code) {
            if (codes.contains(code)) {
                codes.remove(code);
                validators.remove(code);
                codeMessages.remove(code);
                codeResMessages.remove(code);
            }

            return this;
        }

        public TargetHandler build() {
            return new TargetHandler(this);
        }

        private Map<Integer, String> getCodeMessages() {
            return codeMessages;
        }

        private Map<Integer, Integer> getCodeResMessages() {
            return codeResMessages;
        }

        private Map<Integer, ParticularHandler> getValidators() {
            return validators;
        }
    }
}
