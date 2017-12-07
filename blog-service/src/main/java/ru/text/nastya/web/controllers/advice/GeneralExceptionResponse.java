package ru.text.nastya.web.controllers.advice;

public class GeneralExceptionResponse {

    private String requestUrl;

    private String method;

    private String exceptionMessage;

    private String possibleSolution;


    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getPossibleSolution() {
        return possibleSolution;
    }

    public void setPossibleSolution(String possibleSolution) {
        this.possibleSolution = possibleSolution;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GeneralExceptionResponse{");
        sb.append("requestUrl='").append(requestUrl).append('\'');
        sb.append(", method='").append(method).append('\'');
        sb.append(", exceptionMessage='").append(exceptionMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
