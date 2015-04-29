package com.league2.app.Vo;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.league2.app.R;

public class ChampionImageVisitor {

    public Context mContext;
    public Resources mResources;

   public ChampionImageVisitor(Context context) {
       mContext = context;
       mResources = mContext.getResources();
   }

    private Drawable getChampionImage(ChampionVisitor championId) {
       return championId.accept(new ChampionVisitor.Visitor<Drawable>() {

           @Override
           public Drawable visitShaco() {
               return mResources.getDrawable(R.drawable.shaco);
           }

           @Override
           public Drawable visitDrMundo() {
               return null;
           }

           @Override
           public Drawable visitRammus() {
               return null;
           }

           @Override
           public Drawable visitAnivia() {
               return null;
           }

           @Override
           public Drawable visitIrelia() {
               return null;
           }

           @Override
           public Drawable visitYasuo() {
               return null;
           }

           @Override
           public Drawable visitSona() {
               return null;
           }

           @Override
           public Drawable visitKassadin() {
               return null;
           }

           @Override
           public Drawable visitZac() {
               return null;
           }

           @Override
           public Drawable visitGnar() {
               return null;
           }

           @Override
           public Drawable visitKarma() {
               return null;
           }

           @Override
           public Drawable visitCorki() {
               return null;
           }

           @Override
           public Drawable visitGangplank() {
               return null;
           }

           @Override
           public Drawable visitJanna() {
               return null;
           }

           @Override
           public Drawable visitBraum() {
               return null;
           }

           @Override
           public Drawable visitAshe() {
               return null;
           }

           @Override
           public Drawable visitTryndamere() {
               return null;
           }

           @Override
           public Drawable visitJax() {
               return null;
           }

           @Override
           public Drawable visitMorgana() {
               return null;
           }

           @Override
           public Drawable visitZilean() {
               return null;
           }

           @Override
           public Drawable visitSinged() {
               return null;
           }

           @Override
           public Drawable visitEvelynn() {
               return null;
           }

           @Override
           public Drawable visitTwitch() {
               return null;
           }

           @Override
           public Drawable visitGalio() {
               return null;
           }

           @Override
           public Drawable visitVelkoz() {
               return null;
           }

           @Override
           public Drawable visitOlaf() {
               return null;
           }

           @Override
           public Drawable visitAnnie() {
               return null;
           }

           @Override
           public Drawable visitLeblanc() {
               return null;
           }

           @Override
           public Drawable visitKarthus() {
               return null;
           }

           @Override
           public Drawable visitUrgot() {
               return null;
           }

           @Override
           public Drawable visitAmumu() {
               return null;
           }

           @Override
           public Drawable visitXinZhao() {
               return null;
           }

           @Override
           public Drawable visitChogath() {
               return null;
           }

           @Override
           public Drawable visitTwistedFate() {
               return null;
           }

           @Override
           public Drawable visitFiddleSticks() {
               return null;
           }

           @Override
           public Drawable visitVladimir() {
               return null;
           }

           @Override
           public Drawable visitWarwick() {
               return null;
           }

           @Override
           public Drawable visitTeemo() {
               return null;
           }

           @Override
           public Drawable visitTristana() {
               return null;
           }

           @Override
           public Drawable visitSivir() {
               return null;
           }

           @Override
           public Drawable visitSoraka() {
               return null;
           }

           @Override
           public Drawable visitRyze() {
               return null;
           }

           @Override
           public Drawable visitSion() {
               return null;
           }

           @Override
           public Drawable visitMasterYi() {
               return null;
           }

           @Override
           public Drawable visitAlistar() {
               return null;
           }

           @Override
           public Drawable visitMissFortune() {
               return null;
           }

           @Override
           public Drawable visitNunu() {
               return null;
           }

           @Override
           public Drawable visitRengar() {
               return null;
           }

           @Override
           public Drawable visitVolibear() {
               return null;
           }

           @Override
           public Drawable visitFizz() {
               return null;
           }

           @Override
           public Drawable visitGraves() {
               return null;
           }

           @Override
           public Drawable visitAhri() {
               return null;
           }

           @Override
           public Drawable visitLux() {
               return null;
           }

           @Override
           public Drawable visitShyvanna() {
               return null;
           }

           @Override
           public Drawable visitXerath() {
               return null;
           }

           @Override
           public Drawable visitThresh() {
               return null;
           }

           @Override
           public Drawable visitShen() {
               return null;
           }

           @Override
           public Drawable visitJinx() {
               return null;
           }

           @Override
           public Drawable visitKogMaw() {
               return null;
           }

           @Override
           public Drawable visitRiven() {
               return null;
           }

           @Override
           public Drawable visitTalon() {
               return null;
           }

           @Override
           public Drawable visitMalzahar() {
               return null;
           }

           @Override
           public Drawable visitKayle() {
               return null;
           }

           @Override
           public Drawable visitLeona() {
               return null;
           }

           @Override
           public Drawable visitGragas() {
               return null;
           }

           @Override
           public Drawable visitLulu() {
               return null;
           }

           @Override
           public Drawable visitFiora() {
               return null;
           }

           @Override
           public Drawable visitPoppy() {
               return null;
           }

           @Override
           public Drawable visitZiggs() {
               return null;
           }

           @Override
           public Drawable visitUdyr() {
               return null;
           }

           @Override
           public Drawable visitViktor() {
               return null;
           }

           @Override
           public Drawable visitSejuani() {
               return null;
           }

           @Override
           public Drawable visitVarus() {
               return null;
           }

           @Override
           public Drawable visitNautilus() {
               return null;
           }

           @Override
           public Drawable visitDraven() {
               return null;
           }

           @Override
           public Drawable visitMordekaiser() {
               return null;
           }

           @Override
           public Drawable visitYorick() {
               return null;
           }

           @Override
           public Drawable visitPantheon() {
               return null;
           }

           @Override
           public Drawable visitEzreal() {
               return null;
           }

           @Override
           public Drawable visitGaren() {
               return null;
           }

           @Override
           public Drawable visitAkali() {
               return null;
           }

           @Override
           public Drawable visitKennen() {
               return null;
           }

           @Override
           public Drawable visitVayne() {
               return null;
           }

           @Override
           public Drawable visitJayce() {
               return null;
           }

           @Override
           public Drawable visitCassiopeia() {
               return null;
           }

           @Override
           public Drawable visitLissandra() {
               return null;
           }

           @Override
           public Drawable visitRumble() {
               return null;
           }

           @Override
           public Drawable visitKhazix() {
               return null;
           }

           @Override
           public Drawable visitDarius() {
               return null;
           }

           @Override
           public Drawable visitHecarim() {
               return null;
           }

           @Override
           public Drawable visitSkarner() {
               return null;
           }

           @Override
           public Drawable visitLucian() {
               return null;
           }

           @Override
           public Drawable visitHeimerdinger() {
               return null;
           }

           @Override
           public Drawable visitZed() {
               return null;
           }

           @Override
           public Drawable visitNasus() {
               return null;
           }

           @Override
           public Drawable visitNidalee() {
               return null;
           }

           @Override
           public Drawable visitSyndra() {
               return null;
           }

           @Override
           public Drawable visitQuinn() {
               return null;
           }

           @Override
           public Drawable visitJarvanIV() {
               return null;
           }

           @Override
           public Drawable visitRenekton() {
               return null;
           }

           @Override
           public Drawable visitMaokai() {
               return null;
           }

           @Override
           public Drawable visitNocturne() {
               return null;
           }

           @Override
           public Drawable visitKatarina() {
               return null;
           }

           @Override
           public Drawable visitLeeSin() {
               return null;
           }

           @Override
           public Drawable visitMonkeyKing() {
               return null;
           }

           @Override
           public Drawable visitBrand() {
               return null;
           }

           @Override
           public Drawable visitNami() {
               return null;
           }

           @Override
           public Drawable visitElise() {
               return null;
           }

           @Override
           public Drawable visitDiana() {
               return null;
           }

           @Override
           public Drawable visitOrianna() {
               return null;
           }

           @Override
           public Drawable visitAatrox() {
               return null;
           }

           @Override
           public Drawable visitZyra() {
               return null;
           }

           @Override
           public Drawable visitTrundle() {
               return null;
           }

           @Override
           public Drawable visitVeigar() {
               return null;
           }

           @Override
           public Drawable visitTaric() {
               return null;
           }

           @Override
           public Drawable visitCaitlyn() {
               return null;
           }

           @Override
           public Drawable visitBlitzcrank() {
               return null;
           }

           @Override
           public Drawable visitMalphite() {
               return null;
           }

           @Override
           public Drawable visitVi() {
               return null;
           }

           @Override
           public Drawable visitSwain() {
               return null;
           }
       });
    }
}
