<!--Contact page-->
<!DOCTYPE HTML>
<html>
   <head>
       <link type="text/css" rel="stylesheet" href="stylesheet.css"/>
       <title>Let's get to work</title>
       <meta name="viewport" content="width=device-width, initial-scale=1" /> 

    </head>
<div id="wrapper">
    <body>
        <div id="top-row">
            <a href="index.html"><div class="top-column">Home</div></a>
            <a href="resume.html"><div class="top-column">Resume</div></a>
            <a href="recentprojects.html"><div class="top-column">Recent Projects</div></a>
            <a href="contact.jsp"><div class="top-column">Contact Info</div></a>
        </div>
        
        <div id="content">
            <div class="content-subheader">Technology's great when it works. When it doesn't, that's when I come in.</div>
            <div class="content-header">Let's Get to Work.</div>
            <br/>
            <br/>
            <br/>
            <div class="content-subheader">You can reach me here:</div>
            <div class="content-item"><a href="mailto:fidelcastro2017@gmail.com?subject=Let's get to work">fidelcastro2017@gmail.com</a><br>Use subject line "Let's get to work"</div>
            <br/>
            <div class="content-item">LinkedIn: <a href="http://www.linkedin.com/in/fidel-c-680768122" target="_blank">www.linkedin.com/in/fidel-c-680768122</a></div>
            <br/>
            <br/>
            <br/>
            <form action="contactForm" method="post" class="content-item" id="usrform">
	            Name: <input type="text" name="name" placeholder="John Doe">
                <br>
                Phone Number: <input type="tel" name="phone" placeholder="(xxx) xxx-xxxx">
                <br>
                Email Address: <input type="email" name="email" placeholder="johndoe@example.com">
                <br>
                Your Request:
                <br>
                <textarea form="usrform" name="request" rows="6" cols="60" maxlength="500" placeholder="My network has an issue. I would like an estimate on fixing it.... (Max 500 characters)"></textarea>
                <br>
                <input type="submit" value="Submit">
            </form>
            <div class="content-item">I will try to contact you no later than 2 days from the submission of your information.</div>
            <div id="result" class="content-header"><pre>${requestScope.utilOutput}</pre></div>
        </div>
    </body>
</div>
</html>