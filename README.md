# 스택
- Spring boot
- thymeleaf
- lombok
- jsoup  
  <br>

# 개발 컨셉
1. Jsoup을 사용하여 HTML 내용을 크롤링
2. Type이 HTML 태그 제외일경우 Jsoup.clean을 사용하여 태그 제거
3. 정규식으로 알파벳,숫자 제외하고 모두 제거
4. Arrays.stream.sorted를 커스텀하게 정의하여 정렬
5. 숫자,알파벳 리스트를 정규식으로 획득
6. 알파벳,숫자 교차하며 조합
7. 몫,나누기를 계산하여 object 리턴