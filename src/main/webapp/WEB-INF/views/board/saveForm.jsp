<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<%@include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">제목</label> <input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">작성하기</button>
</div>
<script>
      $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 5',
        tabsize: 2,
        height: 300
      });
    </script>
<%@include file="../layout/footer.jsp"%>
<script src="../js/board.js"></script>