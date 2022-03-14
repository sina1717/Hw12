package repository;


import Entity.CreditCard;


public class CreditRepository implements BaseRepository<CreditCard> {
    @Override
    public CreditCard findById(Integer id) {
        try (var session = sessionFactory.openSession()){
            return session.find(CreditCard.class,id);
        }
    }

    public CreditCard findByCardNumber(Long cardNumber){
        String hql = "from Entity.CreditCard where cardNumber = :cardNumber";
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery(hql, CreditCard.class);
            query.setParameter("cardNumber",cardNumber);
            return query.getSingleResult();
        }
    }
}
