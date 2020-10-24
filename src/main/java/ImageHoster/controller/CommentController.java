package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    private String createComment(@PathVariable Integer imageId, @PathVariable String imageTitle, @RequestParam("comment") String comment, HttpSession session){
        User user = (User)session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        Comment newComment = new Comment();
        newComment.setText(comment);
        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setCreatedDate(LocalDate.now());
        commentService.createComment(newComment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
