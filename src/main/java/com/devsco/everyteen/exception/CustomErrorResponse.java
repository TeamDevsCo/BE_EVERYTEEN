package com.devsco.everyteen.exception;

import lombok.Builder;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.List;

public record CustomErrorResponse(
  String title,
  String detail,
  int status,
  List<FieldErrorInfo> invalids
) {

  public CustomErrorResponse(ProblemDetail problem) {
    this(problem.getTitle(), problem.getDetail(), problem.getStatus(), new ArrayList<>());
  }

  public static CustomErrorResponse from(MethodArgumentNotValidException ex) {
    CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getBody());
    errorResponse.invalids.addAll(FieldErrorInfo.from(ex.getAllErrors()));
    return new CustomErrorResponse(ex.getBody());
  }

  public static CustomErrorResponse from(HandlerMethodValidationException ex) {
    CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getBody());
    errorResponse.invalids.addAll(FieldErrorInfo.from(ex.getAllErrors()));
    return new CustomErrorResponse(ex.getBody());
  }

  @Builder
  public record FieldErrorInfo(
    String field,
    String message,
    Object[] arguments,
    String rejectValue,
    String[] codes
  ) {

    public static List<FieldErrorInfo> from(List<? extends MessageSourceResolvable> errors) {
      return errors.stream().map(FieldErrorInfo::from).toList();
    }

    private static FieldErrorInfo from(MessageSourceResolvable error) {
      var builder = FieldErrorInfo.builder();
      if (error instanceof FieldError fieldError) {
        builder.field(fieldError.getField());
      }
      return builder
        .codes(error.getCodes())
        .message(error.getDefaultMessage())
        .arguments(error.getArguments())
        .build();
    }
  }
}
