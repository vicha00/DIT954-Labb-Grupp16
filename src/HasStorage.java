public interface HasStorage<T> {
    
    public void openStorage();
    public void closeStorage();
    public boolean isStorageOpen();
}
