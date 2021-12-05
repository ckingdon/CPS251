public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_item,viewGroup,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        final ExampleItem currentItem=mExampleList.get(i);
        exampleViewHolder.mTextView.setText(currentItem.getmText());
        exampleViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass the data to the fragment
            }
        });
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.texttitle);
        }
    }
    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
              this.mExampleList=exampleList;
}
