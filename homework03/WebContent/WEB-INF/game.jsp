<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Insert title here</title>
</head>
<body>


<body>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/webcomponentsjs/1.1.0/webcomponents-lite.js"></script>
    <div class="main-vertical-container width-constraint">
        <div class="game-title-row">
            <header>
                <h1>Reese's Pieces Clicker</h1>
            </header>
        </div>

        <div class="story-line-row">
            <game-story-book class="story-line-box"></game-story-book>
        </div>


        <div class="resource-name-row">
            <!-- <div class="resource-box-row">-->
                <game-counter>
                 <!--<div>-->
                    <label for="">Reese's Pieces:</label>
                    <label id="count"></label>
                    <!--</div>-->
                </game-counter>  
                <game-button>
                    <button id="action_button">{Action}</button>
                </game-button> 
                <!-- </div>-->
            </div>
            <div class="generator-row">
                <game-generator data-name="tree" data-id="0"
				class="first-generator"></game-generator>

                <game-generator data-name="factory" data-id="1"
				class="second-generator"></game-generator>

                <game-generator data-name="storm" data-id="2"
				class="third-generator"></game-generator>
            </div>
        </div>
        <!-- end of main-vertical-container-->
        
        <script type="text/javascript" src="app.bundle.js"></script>
    </body>
</body>

</html>