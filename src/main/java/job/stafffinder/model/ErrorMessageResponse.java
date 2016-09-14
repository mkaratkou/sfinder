package job.stafffinder.model;

import java.util.List;

public class ErrorMessageResponse {

    private String errorMessage;
    private List<Error> errors;

    public ErrorMessageResponse(String errorMessage, List<Error> errors) {
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public static class Error {

        public Error(String fieldName, String errorMessage) {
            this.fieldName = fieldName;
            this.errorMessage = errorMessage;
        }

        private String fieldName;
        private String errorMessage;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

}
