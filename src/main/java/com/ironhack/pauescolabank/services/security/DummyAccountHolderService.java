{

    ArrayList<AccountHolder> accountHolders = new ArrayList<>();


    public AccountHolder create(AccountHolder accountHolder) {
        var id = new Random().nextLong();
        accountHolder.setId(id);
        accountHolders.add(accountHolder);
        return accountHolder;
    }


    public AccountHolder get(String keycloakId) {
        for (AccountHolder accountHolder : accountHolders) {
            if (accountHolder.getKeycloakId().equals(keycloakId)) {
                return accountHolder;
            }

        }
        return null;
    }
}
