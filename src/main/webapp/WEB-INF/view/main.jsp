<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="../fragments/header.jsp"/>
<body>
<div class="all-team">
    <div class="wrapper-team">
        <div class="team">
            <div class="team-article">
                <h2 class="team-article__h2">A humble Team we are</h2>
                <h3 class="team-article__h3">We'll some of us anyways</h3>
            </div>
            <div class="team-person">
                <div class="team-person_block_1">
                    <div class="block_1__photo">
                        <img src="../../resources/img/team/person1.png" class="block_1_photo__img">
                        <a  href="" class="block_1_photo__a"><img src="../../resources/img/team/twitter.png" class="block_1_photo__a_img"></a>
                    </div>
                    <div class="block_1__article">
                        <h4 class="block_1__article__h4">Josh Jansen</h4>
                        <h5 class="block_1__article__h5">Digital Producer</h5>
                    </div>
                </div>
                <div class="team-person_block_2">
                    <div class="block_2__photo">
                        <img src="../../resources/img/team/person2.png" class="block_2_photo__img">
                        <a  href="" class="block_2_photo__a"><img src="../../resources/img/team/twitter.png" class="block_2_photo__a_img"></a>
                    </div>
                    <div class="block_2__article">
                        <h4 class="block_2__article__h4">Blaz Robar</h4>
                        <h5 class="block_2__article__h5">Designer</h5>
                    </div>
                </div>
                <div class="team-person_block_3">
                    <div class="block_3__photo">
                        <img src="../../resources/img/team/person3.png" class="block_3_photo__img">
                        <a  href="" class="block_3_photo__a"><img src="../../resources/img/team/twitter.png" class="block_3_photo__a_img"></a>
                    </div>
                    <div class="block_3__article">
                        <h4 class="block_3__article__h4">James Brown</h4>
                        <h5 class="block_3__article__h5">Developer</h5>
                    </div>
                </div>
                <div class="team-person_block_4">
                    <div class="block_4__photo">
                        <img src="../../resources/img/team/person4.png" class="block_4_photo__img">
                        <a  href="" class="block_4_photo__a"><img src="../../resources/img/team/twitter.png" class="block_4_photo__a_img"></a>
                    </div>
                    <div class="block_4__article">
                        <h4 class="block_4__article__h4">Skeletor</h4>
                        <h5 class="block_4__article__h5">Administration</h5>
                    </div>
                </div>
            </div>
            <div class="team-slider">
                <span class="team-slider_span">&#9899;</span>
                <span class="team-slider_span">&#9899;</span>
                <span class="team-slider_span">&#9899;</span>
                <span class="team-slider_span">&#9899;</span>
            </div>
        </div>
        <div class="content">
            <div >
                <form method="post" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showCompetitionRate">
                    <input type="hidden" name="category" value="football">
                    <button type="submit" class="content-block_1">Football</button>
                </form>
            </div>
            <div class="content-block_2">
                <form method="post" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showCompetitionRate">
                    <input type="hidden" name="category" value="hockey">
                    <button type="submit">Hockey</button>
                </form>
            </div>
            <div class="content-block_3">
                <form method="post" action="${pageContext.request.contextPath}/totalizator">
                    <input type="hidden" name="command" value="showCompetitionRate">
                    <input type="submit" value="Total"/>
                </form>
            </div>
        </div>
    </div>
</div>





</body>
</html>
