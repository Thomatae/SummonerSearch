package com.league2.app.Vo;

public  enum ChampionVisitor {
    Shaco(35) {
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitShaco();
        }
    },Rammus(33) {
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitRammus();
        }
    },Anivia(34) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAnivia();
        }
    },DrMundo(36) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitDrMundo();
        }
    },Irelia(39) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitIrelia();
        }
    },Yasuo(157) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitYasuo();
        }
    },Sona(37) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSona();
        }
    },Kassadin(38) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKassadin();
        }
    },Zac(154) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitZac();
        }
    },Gnar(150) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitGnar();
        }
    },Karma(43) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKarma();
        }
    },Corki(42) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitCorki();
        }
    },Gangplank(41) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitGangplank();
        }
    },Janna(40) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitJanna();
        }
    },Braum(201) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitBraum();
        }
    },Ashe(22) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAshe();
        }
    },Tryndamere(23) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTryndamere();
        }
    },Jax(24) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitJax();
        }
    },Morgana(25) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMorgana();
        }
    },Zilean(26) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitZilean();
        }
    },Singed(27) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSinged();
        }
    },Evelynn(28) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitEvelynn();
        }
    },Twitch(29) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTwitch();
        }
    },Galio(3) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitGalio();
        }
    },Velkoz(161) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVelkoz();
        }
    },Olaf(2) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitOlaf();
        }
    },Annie(1) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAnnie();
        }
    },Leblanc(7) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLeblanc();
        }
    },Karthus(30) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKarthus();
        }
    },Urgot(6) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitUrgot();
        }
    },Amumu(32) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAmumu();
        }
    },XinZhao(5) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitXinZhao();
        }
    },Chogath(31) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitChogath();
        }
    },TwistedFate(4) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTwistedFate();
        }
    },FiddleSticks(9) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitFiddleSticks();
        }
    },Vladimir(8) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVladimir();
        }
    },Warwick(19) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitWarwick();
        }
    },Teemo(17) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTeemo();
        }
    },Tristana(18) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTristana();
        }
    },Sivir(15) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSivir();
        }
    },Soraka(16) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSoraka();
        }
    },Ryze(13) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitRyze();
        }
    },Sion(14) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSion();
        }
    },MasterYi(11) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMasterYi();
        }
    },Alistar(12) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAlistar();
        }
    },MissFortune(21) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMissFortune();
        }
    },Nunu(20) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitNunu();
        }
    },Rengar(107) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitRengar();
        }
    },Volibear(106) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVolibear();
        }
    },Fizz(105) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitFizz();
        }
    },Graves(104) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitGraves();
        }
    },Ahri(103) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAhri();
        }
    },Lux(99) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLux();
        }
    },Shyvanna(102) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitShyvanna();
        }
    },Xerath(101) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitXerath();
        }
    },Thresh(412) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitThresh();
        }
    },Shen(98) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitShen();
        }
    },Jinx(222) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitJinx();
        }
    },KogMaw(96) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKogMaw();
        }
    },Riven(92) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitRiven();
        }
    },Talon(91) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTalon();
        }
    },Malzahar(90) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMalzahar();
        }
    },Kayle(10) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKayle();
        }
    },Leona(89) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLeona();
        }
    },Gragas(79) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitGragas();
        }
    },Lulu(117) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLulu();
        }
    },Fiora(114) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitFiora();
        }
    },Poppy(78) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitPoppy();
        }
    },Ziggs(115) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitZiggs();
        }
    },Udyr(77) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitUdyr();
        }
    },Viktor(112) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitViktor();
        }
    },Sejuani(113) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSejuani();
        }
    },Varus(110) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVarus();
        }
    },Nautilus(111) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitNautilus();
        }
    },Draven(119) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitDraven();
        }
    },Mordekaiser(82) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMordekaiser();
        }
    },Yorick(83) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitYorick();
        }
    },Pantheon(80) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitPantheon();
        }
    },Ezreal(81) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitEzreal();
        }
    },Garen(86) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitGaren();
        }
    },Akali(84) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAkali();
        }
    },Kennen(85) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKennen();
        }
    },Vayne(67) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVayne();
        }
    },Jayce(126) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitJayce();
        }
    },Cassiopeia(69) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitCassiopeia();
        }
    },Lissandra(127) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLissandra();
        }
    },Rumble(68) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitRumble();
        }
    },Khazix(121) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKhazix();
        }
    },Darius(122) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitDarius();
        }
    },Hecarim(120) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitHecarim();
        }
    },Skarner(72) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSkarner();
        }
    },Lucian(236) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLucian();
        }
    },Heimerdinger(74) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitHeimerdinger();
        }
    },Zed(238) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitZed();
        }
    },Nasus(75) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitNasus();
        }
    },Nidalee(76) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitNidalee();
        }
    },Syndra(134) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSyndra();
        }
    },Quinn(133) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitQuinn();
        }
    },JarvanIV(59) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitJarvanIV();
        }
    },Renekton(58) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitRenekton();
        }
    },Maokai(57) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMaokai();
        }
    },Nocturne(56) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitNocturne();
        }
    },Katarina(55) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitKatarina();
        }
    },LeeSin(64) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitLeeSin();
        }
    },MonkeyKing(62) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMonkeyKing();
        }
    },Brand(63) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitBrand();
        }
    },Nami(267) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitNami();
        }
    },Elise(60) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitElise();
        }
    },Diana(131) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitDiana();
        }
    },Orianna(61) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitOrianna();
        }
    },Aatrox(266) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitAatrox();
        }
    },Zyra(143) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitZyra();
        }
    },Trundle(48) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTrundle();
        }
    },Veigar(45) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVeigar();
        }
    },Taric(44) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitTaric();
        }
    },Caitlyn(51) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitCaitlyn();
        }
    },Blitzcrank(53) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitBlitzcrank();
        }
    },Malphite(54) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitMalphite();
        }
    },Vi(254) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitVi();
        }
    },Swain(50) {
        @Override
        public <T> T accept(Visitor<T> visitor){
            return visitor.visitSwain();
        }
    };

    private int mValue;

    private ChampionVisitor(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


    public abstract <T> T accept(Visitor<T> visitor);

    public interface Visitor<T> {
        T visitShaco();
        T visitDrMundo();
        T visitRammus();
        T visitAnivia();
        T visitIrelia();
        T visitYasuo();
        T visitSona();
        T visitKassadin();
        T visitZac();
        T visitGnar();
        T visitKarma();
        T visitCorki();
        T visitGangplank();
        T visitJanna();
        T visitBraum();
        T visitAshe();
        T visitTryndamere();
        T visitJax();
        T visitMorgana();
        T visitZilean();
        T visitSinged();
        T visitEvelynn();
        T visitTwitch();
        T visitGalio();
        T visitVelkoz();
        T visitOlaf();
        T visitAnnie();
        T visitLeblanc();
        T visitKarthus();
        T visitUrgot();
        T visitAmumu();
        T visitXinZhao();
        T visitChogath();
        T visitTwistedFate();
        T visitFiddleSticks();
        T visitVladimir();
        T visitWarwick();
        T visitTeemo();
        T visitTristana();
        T visitSivir();
        T visitSoraka();
        T visitRyze();
        T visitSion();
        T visitMasterYi();
        T visitAlistar();
        T visitMissFortune();
        T visitNunu();
        T visitRengar();
        T visitVolibear();
        T visitFizz();
        T visitGraves();
        T visitAhri();
        T visitLux();
        T visitShyvanna();
        T visitXerath();
        T visitThresh();
        T visitShen();
        T visitJinx();
        T visitKogMaw();
        T visitRiven();
        T visitTalon();
        T visitMalzahar();
        T visitKayle();
        T visitLeona();
        T visitGragas();
        T visitLulu();
        T visitFiora();
        T visitPoppy();
        T visitZiggs();
        T visitUdyr();
        T visitViktor();
        T visitSejuani();
        T visitVarus();
        T visitNautilus();
        T visitDraven();
        T visitMordekaiser();
        T visitYorick();
        T visitPantheon();
        T visitEzreal();
        T visitGaren();
        T visitAkali();
        T visitKennen();
        T visitVayne();
        T visitJayce();
        T visitCassiopeia();
        T visitLissandra();
        T visitRumble();
        T visitKhazix();
        T visitDarius();
        T visitHecarim();
        T visitSkarner();
        T visitLucian();
        T visitHeimerdinger();
        T visitZed();
        T visitNasus();
        T visitNidalee();
        T visitSyndra();
        T visitQuinn();
        T visitJarvanIV();
        T visitRenekton();
        T visitMaokai();
        T visitNocturne();
        T visitKatarina();
        T visitLeeSin();
        T visitMonkeyKing();
        T visitBrand();
        T visitNami();
        T visitElise();
        T visitDiana();
        T visitOrianna();
        T visitAatrox();
        T visitZyra();
        T visitTrundle();
        T visitVeigar();
        T visitTaric();
        T visitCaitlyn();
        T visitBlitzcrank();
        T visitMalphite();
        T visitVi();
        T visitSwain();

    }
}
