package Facebook.domain.validators;

import Facebook.domain.Friendship;
import Facebook.exceptions.ValidationException;

public class FriendshipValidator implements Validator<Friendship>{
    @Override
    public void validate(Friendship entity) throws ValidationException {
        String message="";
        if(entity.getFriendsFrom()=="")
            message+="date can not be empty.\n";

    }
}
